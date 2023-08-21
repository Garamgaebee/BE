package com.garamgaebee.thread.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateCommentCommand {
    private String rootThreadIdx;
    private String authorIdx;
    private String content;
    private boolean isComment;
}
