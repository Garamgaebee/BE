package com.garamgeabee.member.domain;

import com.garamgeabee.member.domain.dto.CreateMemberCommand;
import com.garamgeabee.member.domain.entity.Member;
import com.garamgeabee.member.domain.ports.out.MemberRepository;
import com.garamgeabee.member.domain.valueobject.MemberStatus;
import com.garamgeabee.member.domain.valueobject.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

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
        return Member.builder()
                .memberIdx(req.getMemberIdx())
                .memberName(req.getMemberName())
                .nickname(req.getNickname())
                .dept(req.getDept())
                .type(MemberType.values()[req.getMemberType()]).build();
    }
}
