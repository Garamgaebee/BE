package com.garamgeabee.member.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Member {

    private UUID memberIdx;

    private String memberName;

    private String nickname;

    private String company;

    private String duty;

    private String level;

    private String profileImgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    private List<Career> careers;

    private List<Email> emails;

    private List<Sns> snses;

    @Builder
    public Member(UUID memberIdx, String memberName, String nickname, String company, String duty, String level,
                  String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status,
                  List<Career> careers, List<Email> emails, List<Sns> snses)
    {
        this.memberIdx = memberIdx;
        this.memberName = memberName;
        this.nickname = nickname;
        this.company = company;
        this.duty = duty;
        this.level = level;
        this.profileImgUrl = profileImgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.careers = careers;
        this.emails = emails;
        this.snses = snses;
    }

    public void deleteMember() {
        this.status = false;
    }

    public void changeProfileImage(String imgUrl) {
        this.profileImgUrl = imgUrl;
    }
}
