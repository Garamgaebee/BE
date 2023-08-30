package com.garamgaebee.member.domain;

import com.garamgaebee.member.domain.dto.CreateMemberCommand;
import com.garamgaebee.member.domain.ports.out.MemberRepository;
import com.garamgaebee.member.domain.valueobject.MemberType;
import com.garamgaebee.member.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CreateMemberHelper {

    private final MemberRepository memberRepository;

    @Autowired
    public CreateMemberHelper(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UUID persistMember(Member member) {
        return memberRepository.persistMember(member);
    }

    public Member createMember(CreateMemberCommand req){
//        log.info(req.toString());
        return Member.builder()
                .memberIdx(req.getMemberIdx())
                .nickname(req.getNickname())
                .dept(req.getDept())
                .type(MemberType.values()[req.getMemberType()]).build();
    }
}
