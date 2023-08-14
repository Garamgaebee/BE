package com.garamgaebee.member.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileImgResponse {
    private String profileImgUrl;

    @Builder
    public ProfileImgResponse(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
