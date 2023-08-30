package com.garamgaebee.member.domain.entity;

import com.garamgaebee.member.domain.valueobject.MemberStatus;
import com.garamgaebee.member.domain.valueobject.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
public class Member {

    private UUID memberIdx;

    private String nickname;

    private String dept;

    private MemberType type;

    private String company;

    private String duty;

    private String level;

    private String profileImgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private MemberStatus status;

    private List<Career> careers;

    private List<Email> emails;

    private List<Sns> snses;

    @Builder
    public Member(UUID memberIdx, String nickname, String dept, MemberType type, String company, String duty, String level,
                  String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, MemberStatus status,
                  List<Career> careers, List<Email> emails, List<Sns> snses)
    {
        this.memberIdx = memberIdx;
        this.nickname = nickname;
        this.dept = dept;
        this.type = type;
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
        this.status = MemberStatus.DELETE;
    }

    public void updateProfileImg(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
