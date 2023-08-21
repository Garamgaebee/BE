package com.garamgaebee.thread.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteLikeRes {
    private Boolean deleteSuccess;
    private String targetThreadId;

    @Builder
    public DeleteLikeRes(Boolean deleteSuccess, String targetThreadId) {
        this.deleteSuccess = deleteSuccess;
        this.targetThreadId = targetThreadId;
    }
}
