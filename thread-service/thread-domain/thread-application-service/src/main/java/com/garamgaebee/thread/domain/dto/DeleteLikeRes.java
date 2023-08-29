package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteLikeRes {
    @Schema(name = "deleteSuccess", description = "삭제 성공 여부", example = "true")
    private Boolean deleteSuccess;
    @Schema(name = "targetThreadId", description = "대상 스레드 인덱스", example = "UUID String")
    private String targetThreadId;

    @Builder
    public DeleteLikeRes(Boolean deleteSuccess, String targetThreadId) {
        this.deleteSuccess = deleteSuccess;
        this.targetThreadId = targetThreadId;
    }
}
