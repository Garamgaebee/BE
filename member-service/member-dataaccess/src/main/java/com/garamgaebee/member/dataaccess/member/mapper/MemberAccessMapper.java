package com.garamgaebee.member.dataaccess.member.mapper;

import com.garamgaebee.member.dataaccess.member.entity.CareerEntity;
import com.garamgaebee.member.dataaccess.member.entity.EmailEntity;
import com.garamgaebee.member.dataaccess.member.entity.MemberEntity;
import com.garamgaebee.member.dataaccess.member.entity.SnsEntity;
import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.valueobject.MemberCareer;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberAccessMapper {
    public GetMemberResponse getMemberMapper(MemberEntity member, @Nullable List<EmailEntity> emails, @Nullable List<CareerEntity> careers, @Nullable List<SnsEntity> snses) {
        
        return GetMemberResponse.builder()
                .memberIdx(member.getMemberIdx())
                .memberName(member.getMemberName())
                .nickname(member.getNickname())
                .company(member.getCompany())
                .duty(member.getDuty())
                .level(member.getLevel())
                .profileImgUrl(member.getProfileImgUrl())
                .email(emails.stream().map(EmailEntity::getEmail).collect(Collectors.toList()))
                .sns(snses.stream().map(SnsEntity::getSns).collect(Collectors.toList()))
                .career(careers.stream().map(career ->
                        new MemberCareer(
                                career.getCareerName(),
                                career.getIsProgress(),
                                career.getSinceDate(),
                                career.getEndDate())).collect(Collectors.toList())).build();
    }
}
