package com.garamgeabee.member.domain;

import com.garamgaebee.common.exception.BaseException;
import com.garamgeabee.member.domain.dto.DeleteMemberResponse;
import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.dto.PatchMemberImgCommand;
import com.garamgeabee.member.domain.entity.Member;
import com.garamgeabee.member.domain.mapper.MemberDataMapper;
import com.garamgeabee.member.domain.ports.in.MemberService;
import com.garamgeabee.member.domain.ports.out.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Slf4j
@Service
@ComponentScan
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberDataMapper memberDataMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberDataMapper memberDataMapper) {
        this.memberRepository = memberRepository;
        this.memberDataMapper = memberDataMapper;
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
    public boolean insertMemberImage(PatchMemberImgCommand req) throws BaseException{

        Member member = memberRepository.findMember(UUID.fromString(req.getMemberIdx()));
        member.changeProfileImage(req.getImgUrl());

        return memberRepository.patchMemberImage(member);
    }

    @Override
    @Transactional
    public UUID createMember(UUID memberIdx) {
        return memberRepository.createMember(memberIdx);
    }
}
