package com.garamgaebee.thread.domain.dto;

import com.garamgaebee.thread.domain.entity.ThreadType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFeignTeamThreadsRes {
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
    private String memberProfileUrl;
    private String teamProfileUrl;
    private ThreadType type;
    private String createdAt;

    @Builder
    public GetFeignTeamThreadsRes(String threadIdx, String authorIdx, String authorName, String content, int likeNumber, int commentNumber, boolean isComment, Long teamId, String teamName, String parentIdx, List<String> imgUrls, String memberProfileUrl, String teamProfileUrl, ThreadType type, String createdAt) {
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
        this.memberProfileUrl = memberProfileUrl;
        this.teamProfileUrl = teamProfileUrl;
        this.type = type;
        this.createdAt = createdAt;
    }
}
