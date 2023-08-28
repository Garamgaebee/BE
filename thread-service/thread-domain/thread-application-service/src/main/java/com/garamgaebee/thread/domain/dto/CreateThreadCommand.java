package com.garamgaebee.thread.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateThreadCommand {
    private String authorIdx;
    private String content;
    private boolean isComment;
}
