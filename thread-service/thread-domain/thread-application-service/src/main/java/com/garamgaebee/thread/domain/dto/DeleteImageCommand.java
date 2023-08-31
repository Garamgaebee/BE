package com.garamgaebee.thread.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeleteImageCommand {
    @Schema(name = "urlList", description = "삭제 대상 url 리스트 (하나만 지워도 리스트로 보내주세요)", example = "url String List")
    List<String> urlList = new ArrayList<>();
}
