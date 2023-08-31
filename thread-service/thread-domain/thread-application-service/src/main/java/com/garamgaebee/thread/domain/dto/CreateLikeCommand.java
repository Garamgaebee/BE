package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateLikeCommand {
    @Schema(name = "threadIdx", description = "대상 스레드, 댓글 인덱스", example = "UUID String")
    private String threadIdx;
    @Schema(name = "memberIdx", description = "좋아요 누르는 멤버 인덱스", example = "UUID String")
    private String memberIdx;

    public CreateLikeCommand(String theradIdx, String memberIdx) {
        this.threadIdx = theradIdx;
        this.memberIdx = memberIdx;
    }
}
