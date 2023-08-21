package com.garamgaebee.thread.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateLikeRes {
    private Boolean likeSuccess;
    private String targetThreadIdx;

    @Builder
    public CreateLikeRes(Boolean likeSuccess, String targetThreadIdx) {
        this.likeSuccess = likeSuccess;
        this.targetThreadIdx = targetThreadIdx;
    }
}
