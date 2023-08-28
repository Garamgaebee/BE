package com.garamgaebee.thread.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteLikeCommand {
    private String threadIdx;
    private String memberIdx;

    public DeleteLikeCommand(String threadIdx, String memberIdx) {
        this.threadIdx = threadIdx;
        this.memberIdx = memberIdx;
    }
}
