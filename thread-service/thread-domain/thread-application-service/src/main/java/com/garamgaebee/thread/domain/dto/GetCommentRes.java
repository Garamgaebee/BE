package com.garamgaebee.thread.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetCommentRes {
    private String authorIdx;
    private String commentId;
    private String rootThreadId;
    private String memberProfileUrl;
    private String teamProfileUrl;
    private String memberName;
    private String department;
    private String teamName;
    private List<String> imgUrls;
    private String content;
    private int likeNumber;
    private String createdAt;
    @JsonProperty("isComment")
    private Boolean isComment;

    @Builder
    public GetCommentRes(String authorIdx, String commentId, String rootThreadId, String memberProfileUrl, String teamProfileUrl, String memberName, String department, String teamName, List<String> imgUrls, String content, int likeNumber, String createdAt, Boolean isComment) {
        this.authorIdx = authorIdx;
        this.commentId = commentId;
        this.rootThreadId = rootThreadId;
        this.memberProfileUrl = memberProfileUrl;
        this.teamProfileUrl = teamProfileUrl;
        this.memberName = memberName;
        this.department = department;
        this.teamName = teamName;
        this.imgUrls = imgUrls;
        this.content = content;
        this.likeNumber = likeNumber;
        this.createdAt = createdAt;
        this.isComment = isComment;
    }
}
