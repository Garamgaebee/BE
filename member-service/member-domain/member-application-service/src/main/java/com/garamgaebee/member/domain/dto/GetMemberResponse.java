package com.garamgaebee.member.domain.dto;

import com.garamgaebee.member.domain.valueobject.MemberCareer;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class GetMemberResponse {
    private UUID memberIdx;

    private String memberName;

    private String nickname;

    private String company;

    private String duty;

    private String level;

    private String profileImgUrl;

    private List<String> email;

    private List<String> sns;

    private List<MemberCareer> career;

    @Builder
    public GetMemberResponse(UUID memberIdx, String memberName, String nickname, String company, String duty,
                             String level, String profileImgUrl, List<String> email, List<String> sns, List<MemberCareer> career) {
        this.memberIdx = memberIdx;
        this.memberName = memberName;
        this.nickname = nickname;
        this.company = company;
        this.duty = duty;
        this.level = level;
        this.profileImgUrl = profileImgUrl;
        this.email = email;
        this.sns = sns;
        this.career = career;
    }
}
