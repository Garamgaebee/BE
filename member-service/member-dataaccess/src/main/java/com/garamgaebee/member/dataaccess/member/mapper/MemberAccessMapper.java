package com.garamgaebee.member.dataaccess.member.mapper;

import com.garamgaebee.member.dataaccess.member.entity.CareerEntity;
import com.garamgaebee.member.dataaccess.member.entity.EmailEntity;
import com.garamgaebee.member.dataaccess.member.entity.MemberEntity;
import com.garamgaebee.member.dataaccess.member.entity.SnsEntity;
import com.garamgaebee.member.domain.entity.Career;
import com.garamgaebee.member.domain.entity.Email;
import com.garamgaebee.member.domain.entity.Member;
import com.garamgaebee.member.domain.entity.Sns;
import com.garamgaebee.member.domain.valueobject.MemberStatus;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberAccessMapper {

    public Member getMemberMapper(MemberEntity member, @Nullable List<EmailEntity> emails, @Nullable List<CareerEntity> careers, @Nullable List<SnsEntity> snses){
        return Member.builder()
                .memberIdx(member.getMemberIdx())
                .memberName(member.getMemberName())
                .nickname(member.getNickname())
                .company(member.getCompany())
                .duty(member.getDuty())
                .level(member.getLevel())
                .profileImgUrl(member.getProfileImgUrl())
                .emails(emailListMapper(emails))
                .careers(careerListMapper(careers))
                .snses(snsListMapper(snses))
                .build();
    }

    private List<Email> emailListMapper(List<EmailEntity> emails){
        if (emails.isEmpty()) return new ArrayList<>();

        return emails.stream().map(email ->
                        Email.builder()
                                .emailIdx(email.getEmailIdx())
                                .email(email.getEmail())
                                .createdAt(email.getCreatedAt())
                                .updatedAt(email.getUpdatedAt())
                                .status(email.getStatus()).build())
                .collect(Collectors.toList());
    }

    private List<Career> careerListMapper(List<CareerEntity> careers){
        if(careers.isEmpty()) return new ArrayList<>();

        return careers.stream().map(career ->
                Career.builder()
                        .careerIdx(career.getCareerIdx())
                        .careerName(career.getCareerName())
                        .isProgress(career.getIsProgress())
                        .sinceDate(career.getSinceDate())
                        .endDate(career.getEndDate())
                        .createdAt(career.getCreatedAt())
                        .updatedAt(career.getUpdatedAt())
                        .status(career.getStatus()).build())
                .collect(Collectors.toList());
    }

    private List<Sns> snsListMapper(List<SnsEntity> snses){
        if(snses.isEmpty()) return new ArrayList<>();

        return snses.stream().map(sns ->
                Sns.builder()
                        .snsIdx(sns.getSnsIdx())
                        .sns(sns.getSns())
                        .createdAt(sns.getCreatedAt())
                        .updatedAt(sns.getUpdatedAt())
                        .status(sns.getStatus()).build())
                .collect(Collectors.toList());
    }

    public MemberEntity memberToEntity(Member member){
        return MemberEntity.builder()
                .memberIdx(member.getMemberIdx())
                .memberName(member.getMemberName())
                .nickname(member.getNickname())
                .dept(member.getDept())
                .type(member.getType())
                .status(MemberStatus.ACTIVE)
                .build();
    }

}

