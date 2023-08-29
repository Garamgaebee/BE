package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteThreadRes {
    @Schema(name = "deleteSuccess", description = "삭제 성공 여부", example = "true")
    private boolean deleteSuccess;
}
