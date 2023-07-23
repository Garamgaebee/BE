package com.garamgaebee.memberserivce.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMemberResponse {
    private Long memberIdx;
    private String nickname;
    private String profileEmail;
    private String belong;
    private String content;
    private String profileUrl;

    @Builder
    public GetMemberResponse(Long memberIdx, String nickname, String profileEmail, String belong, String content, String profileUrl) {
        this.memberIdx = memberIdx;
        this.nickname = nickname;
        this.profileEmail = profileEmail;
        this.belong = belong;
        this.content = content;
        this.profileUrl = profileUrl;
    }
}
