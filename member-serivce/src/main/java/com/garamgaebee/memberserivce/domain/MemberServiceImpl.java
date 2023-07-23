package com.garamgaebee.memberserivce.domain;

import com.garamgaebee.memberserivce.dataaccess.member.entity.Member;
import com.garamgaebee.memberserivce.domain.dto.DeleteMemberCommand;
import com.garamgaebee.memberserivce.domain.dto.DeleteMemberResponse;
import com.garamgaebee.memberserivce.domain.dto.GetMemberResponse;
import com.garamgaebee.memberserivce.domain.mapper.MemberDataMapper;
import com.garamgaebee.memberserivce.domain.ports.in.MemberService;
import com.garamgaebee.memberserivce.domain.ports.out.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberDataMapper memberDataMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberDataMapper memberDataMapper) {
        this.memberRepository = memberRepository;
        this.memberDataMapper = memberDataMapper;
    }

    @Override
    public GetMemberResponse getMember(Long memberIdx) {
        Optional<Member> member = memberRepository.findById(memberIdx);

        return memberDataMapper.getMemberMapper(member.get());
    }

    @Override
    public DeleteMemberResponse deleteMember(Long memberIdx) {
        Optional<Member> member = memberRepository.findById(memberIdx);

        member.get().deleteMember();

        return new DeleteMemberResponse(true);
    }
}
