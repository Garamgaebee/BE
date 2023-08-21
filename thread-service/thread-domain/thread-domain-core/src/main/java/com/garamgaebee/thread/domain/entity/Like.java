package com.garamgaebee.thread.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    private String targetThreadIdx;
    private String memberIdx;

    @Builder
    public Like(String targetThreadIdx, String memberIdx) {
        this.targetThreadIdx = targetThreadIdx;
        this.memberIdx = memberIdx;
    }
}
