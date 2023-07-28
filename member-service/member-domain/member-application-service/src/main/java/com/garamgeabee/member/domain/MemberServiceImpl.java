package com.garamgeabee.member.domain;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgeabee.member.domain.dto.DeleteMemberResponse;
import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.dto.PatchMemberImgCommand;
import com.garamgeabee.member.domain.mapper.MemberDataMapper;
import com.garamgeabee.member.domain.ports.in.MemberService;
import com.garamgeabee.member.domain.ports.out.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


import java.util.Optional;
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
    public GetMemberResponse getMember(UUID memberIdx) throws BaseException {
        Optional<GetMemberResponse> member = memberRepository.findMember(memberIdx);
        member.orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));
        return member.get();
    }

    @Override
    public DeleteMemberResponse deleteMember(UUID memberIdx) {
        Boolean isDelete = memberRepository.deleteMember(memberIdx);

        return new DeleteMemberResponse(isDelete);
    }

    @Override
    public boolean insertMemberImage(PatchMemberImgCommand req) {

        return memberRepository.patchMemberImage(req.getMemberIdx(), req.getImgUrl());
    }

    @Override
    public UUID createMember(UUID memberIdx) {
        return memberRepository.createMember(memberIdx);
    }
}
