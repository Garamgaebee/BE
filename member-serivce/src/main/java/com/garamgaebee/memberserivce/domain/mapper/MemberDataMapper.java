package com.garamgaebee.memberserivce.domain.mapper;

import com.garamgaebee.memberserivce.dataaccess.member.entity.Member;
import com.garamgaebee.memberserivce.domain.dto.GetMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberDataMapper {
    public GetMemberResponse getMemberMapper(Member member) {

        return GetMemberResponse.builder()
                .memberIdx(member.getMemberIdx())
                .nickname(member.getNickname())
                .profileEmail(member.getEmail())
                .belong(member.getBelong())
                .content(member.getContent())
                .profileUrl(member.getProfileUrl()).build();
    }
}
