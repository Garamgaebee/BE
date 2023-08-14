package com.garamgaebee.thread.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thread {
    private String threadIdx;
    private String authorIdx;
    private String content;
    private int likeNumber;
    private boolean isComment;
    private String parentIdx;
    private List<String> imgUrls;
    private String memberProfileImg;
    private String teamProfileImg;

    @Builder
    public Thread(String threadIdx, String authorIdx, String content, int likeNumber, boolean isComment, String parentIdx, List<String> imgUrls, String memberProfileImg, String teamProfileImg) {
        this.threadIdx = threadIdx;
        this.authorIdx = authorIdx;
        this.content = content;
        this.likeNumber = likeNumber;
        this.isComment = isComment;
        this.parentIdx = parentIdx;
        this.imgUrls = imgUrls;
        this.memberProfileImg = memberProfileImg;
        this.teamProfileImg = teamProfileImg;
    }
}
