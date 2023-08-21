package com.garamgaebee.thread.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thread {
    private String threadIdx;
    private String authorIdx;
    private String authorName;
    private String content;
    private int likeNumber;
    private int commentNumber;
    private boolean isComment;
    private Long teamId;
    private String teamName;
    private String parentIdx;
    private List<String> imgUrls;
    private String memberProfileImg;
    private String teamProfileImg;
    private ThreadType type;
    private LocalDateTime createdAt;

    @Builder
    public Thread(String threadIdx, String authorIdx, String authorName, String content, int likeNumber, int commentNumber, boolean isComment, Long teamId, String teamName, String parentIdx, List<String> imgUrls, String memberProfileImg, String teamProfileImg, ThreadType type, LocalDateTime createdAt) {
        this.threadIdx = threadIdx;
        this.authorIdx = authorIdx;
        this.authorName = authorName;
        this.content = content;
        this.likeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.isComment = isComment;
        this.teamId = teamId;
        this.teamName = teamName;
        this.parentIdx = parentIdx;
        this.imgUrls = imgUrls;
        this.memberProfileImg = memberProfileImg;
        this.teamProfileImg = teamProfileImg;
        this.type = type;
        this.createdAt = createdAt;
    }
}
