package com.garamgaebee.teammessaging.member.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString
public class GetFeignMemberResponse {
    private UUID memberIdx;

    private String nickname;

    private String dept;

    private String company;

    private String duty;

    private String level;

    private String profileImgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String status;

    private List<MemberCareer> career;

    private List<String> email;

    private List<String> sns;

    @Builder
    public GetFeignMemberResponse(UUID memberIdx, String nickname, String dept, String company, String duty,
                                  String level, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt,
                                  String status, List<MemberCareer> career, List<String> email, List<String> sns) {
        this.memberIdx = memberIdx;
        this.nickname = nickname;
        this.dept = dept;
        this.company = company;
        this.duty = duty;
        this.level = level;
        this.profileImgUrl = profileImgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.career = career;
        this.email = email;
        this.sns = sns;
    }
}
