package com.garamgaebee.thread.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetThreadListRes {
    @JsonProperty("isComment")
    private Boolean isComment;
    private String authorIdx;
    private String threadId;
    //스레드이면 NONE
    private String rootThreadId;
    private String memberProfileUrl;
    //팀 게시물이 아니면 NONE
    private String teamProfileUrl;
    private String memberName;
    private String department;
    private String teamName;
    private List<String> imgUrls;
    private String content;
    private int likeNumber;
    //댓글이면 -1
    private int commentNumber;
    private String createdAt;

    @Builder
    public GetThreadListRes(Boolean isComment, String authorIdx, String threadId, String rootThreadId, String memberProfileUrl, String teamProfileUrl, String memberName, String department, String teamName, List<String> imgUrls, String content, int likeNumber, int commentNumber, String createdAt) {
        this.isComment = isComment;
        this.authorIdx = authorIdx;
        this.threadId = threadId;
        this.rootThreadId = rootThreadId;
        this.memberProfileUrl = memberProfileUrl;
        this.teamProfileUrl = teamProfileUrl;
        this.memberName = memberName;
        this.department = department;
        this.teamName = teamName;
        this.imgUrls = imgUrls;
        this.content = content;
        this.likeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.createdAt = createdAt;
    }
}
