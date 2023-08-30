package com.garamgaebee.member.domain;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.member.domain.dto.*;
import com.garamgaebee.member.domain.entity.Member;
import com.garamgaebee.member.domain.mapper.MemberDataMapper;
import com.garamgaebee.member.domain.ports.in.MemberService;
import com.garamgaebee.member.domain.ports.out.ImageFeignPublisher;
import com.garamgaebee.member.domain.ports.out.MemberRepository;
import com.garamgaebee.member.domain.valueobject.ImageDeleteVO;
import com.garamgaebee.member.domain.valueobject.ImageVO;
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
    private final ImageFeignPublisher imageFeignPublisher;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberDataMapper memberDataMapper, CreateMemberHelper createMemberHelper, ImageFeignPublisher imageFeignPublisher) {
        this.memberRepository = memberRepository;
        this.memberDataMapper = memberDataMapper;
        this.createMemberHelper = createMemberHelper;
        this.imageFeignPublisher = imageFeignPublisher;
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
        String url = uploadImage(file);

        Member member = memberRepository.findMember(UUID.fromString(memberIdx));

        member.updateProfileImg(url);

        memberRepository.patchMemberImage(member);

        return ProfileImgResponse.builder().profileImgUrl(member.getProfileImgUrl()).build();
    }

    /**
     * 이미지 변경 요청
     * */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public ProfileImgResponse patchProfileImg(String memberIdx, MultipartFile file) throws BaseException{
        String url = uploadImage(file);

        Member member = memberRepository.findMember(UUID.fromString(memberIdx));

        boolean isDeleted = deleteImage(member.getProfileImgUrl());

        if(!isDeleted) {
            throw new BaseException(BaseErrorCode.FAIL_TO_DELETE_PROFILE_IMAGE);
        }

        member.updateProfileImg(url);

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

    @Override
    public List<GetFeignMemberResponse> getTeamMembers(List<String> members) {
        List<UUID> memberIdxList = new ArrayList<>();

        for (String member : members) {
            memberIdxList.add(UUID.fromString(member));
        }

        List<Member> memberList = memberRepository.findMemberList(memberIdxList);

        return memberDataMapper.getTeamMemberList(memberList);
    }

    /**
     * Feign 이미지 등록 요청
     * */
    public String uploadImage(MultipartFile file) {
        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file);

        //Image Service에 이미지 등록 요청
        ImageVO vo = imageFeignPublisher.getFeignImageUrls(fileList);

        return vo.getUrl().get(0);
    }

    public Boolean deleteImage(String url) {
        List<String> urlList = new ArrayList<>();
        urlList.add(url);

        DeleteImageCommand command = new DeleteImageCommand(urlList);

        ImageDeleteVO vo = imageFeignPublisher.deleteFeignImages(command);

        return vo.getIsDeleted().get(0);
    }
}
