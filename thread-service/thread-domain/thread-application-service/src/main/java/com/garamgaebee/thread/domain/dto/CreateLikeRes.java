package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateLikeRes {
    @Schema(name = "likeSuccess", description = "요청 성공 여부", example = "true")
    private Boolean likeSuccess;
    @Schema(name = "targetThreadIdx", description = "좋아요 대상 스레드, 댓글 인덱스", example = "UUID String")
    private String targetThreadIdx;

    @Builder
    public CreateLikeRes(Boolean likeSuccess, String targetThreadIdx) {
        this.likeSuccess = likeSuccess;
        this.targetThreadIdx = targetThreadIdx;
    }
}
