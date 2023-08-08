package com.garamgeabee.member.domain;

import com.garamgaebee.common.exception.BaseException;
import com.garamgeabee.member.domain.dto.*;
import com.garamgeabee.member.domain.entity.Member;
import com.garamgeabee.member.domain.mapper.MemberDataMapper;
import com.garamgeabee.member.domain.ports.in.MemberService;
import com.garamgeabee.member.domain.ports.out.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@ComponentScan
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberDataMapper memberDataMapper;

    private final CreateMemberHelper createMemberHelper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberDataMapper memberDataMapper, CreateMemberHelper createMemberHelper) {
        this.memberRepository = memberRepository;
        this.memberDataMapper = memberDataMapper;
        this.createMemberHelper = createMemberHelper;
    }

    @Override
    @Transactional
    public GetMemberResponse getMember(UUID memberIdx) throws BaseException {
        Member member = memberRepository.findMember(memberIdx);

        return memberDataMapper.getMemberMapper(member);
    }

    @Override
    @Transactional
    public DeleteMemberResponse deleteMember(UUID memberIdx) throws BaseException{

        Member member = memberRepository.findMember(memberIdx);
        member.deleteMember();

        return new DeleteMemberResponse(memberRepository.deleteMember(member));
    }

    @Override
    @Transactional
    public UUID createMember(CreateMemberCommand req) {

        Member member = createMemberHelper.createMember(req);

        return createMemberHelper.persistMember(member);
    }

    /**
     * 이미지 등록 요청
     * */
    @Override
    @Transactional
    public ProfileImgResponse postProfileImg(String memberIdx, MultipartFile file) {
        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file);

        List<String> urls = new ArrayList<>();

        Member member = memberRepository.findMember(UUID.fromString(memberIdx));

        //TODO 이미지 등록 요청하기
        member.updateProfileImg(urls.get(0));

        memberRepository.patchMemberImage(member);

        return ProfileImgResponse.builder().profileImgUrl(member.getProfileImgUrl()).build();
    }

    /**
     * 이미지 변경 요청
     * */
    @Override
    @Transactional
    public ProfileImgResponse patchProfileImg(String memberIdx, MultipartFile file) {
        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file);

        List<String> urls = new ArrayList<>();

        Member member = memberRepository.findMember(UUID.fromString(memberIdx));
        //TODO 이미지 삭제 요청하기
        //TODO 이미지 등록 요청하기
        member.updateProfileImg(urls.get(0));

        memberRepository.patchMemberImage(member);

        return ProfileImgResponse.builder().profileImgUrl(member.getProfileImgUrl()).build();
    }

    /**
     * 닉네임 중복 체크
     * */
    @Override
    @Transactional
    public boolean checkDuplicateNickname(String nickname) {

        return memberRepository.checkNicknameExist(nickname);
    }

    /**
     * Feign member 조회
     * */
    @Override
    @Transactional
    public GetFeignMemberResponse getFeignMember(String userIdx) {
        Member member = memberRepository.findMember(UUID.fromString(userIdx));

        return memberDataMapper.getFeignMemberMapper(member);
    }
}
