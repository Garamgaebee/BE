package com.garamgaebee.thread.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateThreadCommand {
    private String threadIdx;
    private String authorIdx;
    private String content;
    private boolean isComment;
    private String memberProfileImg;
    private String teamProfileImg;
}
