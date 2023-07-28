package com.garamgeabee.member.domain.dto;

import com.garamgeabee.member.domain.valueobject.MemberCareer;
import jakarta.annotation.Nullable;
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
    @Nullable
    private List<String> email;
    @Nullable
    private List<String> sns;
    @Nullable
    private List<MemberCareer> career;

    @Builder
    public GetMemberResponse(UUID memberIdx, String memberName, String nickname, String company, String duty, String level, String profileImgUrl, @Nullable List<String> email, @Nullable List<String> sns, @Nullable List<MemberCareer> career) {
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
