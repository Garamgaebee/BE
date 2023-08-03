package com.garamgeabee.member.domain.mapper;


import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.entity.Email;
import com.garamgeabee.member.domain.entity.Member;
import com.garamgeabee.member.domain.entity.Sns;
import com.garamgeabee.member.domain.valueobject.MemberCareer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MemberDataMapper {
    public GetMemberResponse getMemberMapper(Member member) {

        return GetMemberResponse.builder()
                .memberIdx(member.getMemberIdx())
                .memberName(member.getMemberName())
                .nickname(member.getNickname())
                .company(member.getCompany())
                .duty(member.getDuty())
                .level(member.getLevel())
                .profileImgUrl(member.getProfileImgUrl())
                .email(member.getEmails().stream().map(Email::getEmail).collect(Collectors.toList()))
                .sns(member.getSnses().stream().map(Sns::getSns).collect(Collectors.toList()))
                .career(member.getCareers().stream().map(career ->
                        new MemberCareer(
                                career.getCareerName(),
                                career.getIsProgress(),
                                career.getSinceDate(),
                                career.getEndDate())).collect(Collectors.toList())).build();
    }
}
