package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteLikeCommand {
    @Schema(name = "threadIdx", description = "대상 스레드 인덱스", example = "UUID String")
    private String threadIdx;
    @Schema(name = "memberIdx", description = "좋아요 삭제하는 유저 인덱스", example = "UUID String")
    private String memberIdx;

    public DeleteLikeCommand(String threadIdx, String memberIdx) {
        this.threadIdx = threadIdx;
        this.memberIdx = memberIdx;
    }
}
