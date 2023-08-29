package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateCommentCommand {
    @Schema(name = "rootThreadIdx", description = "댓글이 달리는 스레드(게시글) 인덱스(String)", example = "UUID String")
    private String rootThreadIdx;
    @Schema(name = "authorIdx", description = "작성자 인덱스 (String)", example = "UUID String")
    private String authorIdx;
    @Schema(name = "content", description = "댓글 내용", example = "댓글 내용입니다아")
    private String content;
    @Schema(name = "isComment", description = "댓글인지 아닌지 (댓글: true, 댓글 아님: false)", example = "true")
    private boolean isComment;
}
