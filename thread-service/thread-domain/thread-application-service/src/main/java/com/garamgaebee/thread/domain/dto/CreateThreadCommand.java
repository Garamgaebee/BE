package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateThreadCommand {
    @Schema(name = "authorIdx", description = "작성자 인덱스", example = "UUID String")
    private String authorIdx;
    @Schema(name = "content", description = "스레드 본문", example = "본문 내용")
    private String content;
    @Schema(name = "isComment", description = "댓글: true, 댓글 아님: false", example = "false")
    private boolean isComment;
}
