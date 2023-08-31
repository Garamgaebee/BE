package com.garamgaebee.member.domain.mapper;


import com.garamgaebee.member.domain.dto.GetFeignMemberResponse;
import com.garamgaebee.member.domain.dto.GetMemberResponse;
import com.garamgaebee.member.domain.entity.Email;
import com.garamgaebee.member.domain.entity.Member;
import com.garamgaebee.member.domain.entity.Sns;
import com.garamgaebee.member.domain.valueobject.MemberCareer;
import com.garamgaebee.member.domain.valueobject.MemberStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberDataMapper {
    /**
     * api용 mapper
     * */
    public GetMemberResponse getMemberMapper(Member member) {

        return GetMemberResponse.builder()
                .memberIdx(member.getMemberIdx())
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

    /**
     * Feign 용 mapper
     * */
    public GetFeignMemberResponse getFeignMemberMapper(Member member) {

        return GetFeignMemberResponse.builder()
                .memberIdx(member.getMemberIdx())
                .nickname(member.getNickname())
                .dept(member.getDept())
                .company(member.getCompany())
                .duty(member.getDuty())
                .level(member.getLevel())
                .profileImgUrl(member.getProfileImgUrl())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .status(member.getStatus().toString())
                .email(member.getEmails().stream().map(Email::getEmail).collect(Collectors.toList()))
                .sns(member.getSnses().stream().map(Sns::getSns).collect(Collectors.toList()))
                .career(member.getCareers().stream().map(career ->
                        new MemberCareer(
                                career.getCareerName(),
                                career.getIsProgress(),
                                career.getSinceDate(),
                                career.getEndDate())).collect(Collectors.toList())).build();
    }

    /**
     * Team Member Feign용 mapper
     * */
    public List<GetFeignMemberResponse> getTeamMemberList(List<Member> memberList) {

        List<GetFeignMemberResponse> resList = new ArrayList<>();

        if(memberList.isEmpty()) return resList;

        for (Member member : memberList) {
            resList.add(getFeignMemberMapper(member));
        }

        return resList;
    }
}
