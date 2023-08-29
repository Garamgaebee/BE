package com.garamgaebee.thread.domain.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class GetFeignTeamThreadsRes {
    private String content;
    private int likeCount;
    private int commentCount;
    private LocalDateTime date;

    @Builder
    public GetFeignTeamThreadsRes(String content, int likeCount, int commentCount, LocalDateTime date) {
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.date = date;
    }
}
