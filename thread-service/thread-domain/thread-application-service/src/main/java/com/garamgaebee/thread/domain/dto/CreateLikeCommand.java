package com.garamgaebee.thread.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateLikeCommand {
    private String theradIdx;
    private String memberIdx;

    public CreateLikeCommand(String theradIdx, String memberIdx) {
        this.theradIdx = theradIdx;
        this.memberIdx = memberIdx;
    }
}
