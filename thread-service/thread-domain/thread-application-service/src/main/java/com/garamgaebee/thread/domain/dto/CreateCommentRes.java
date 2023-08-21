package com.garamgaebee.thread.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCommentRes {
    private String threadId;
    @JsonProperty(value = "isComment")
    private Boolean isComment;
    private String rootThreadIdx;
    private String authorName;
    private String teamName;
    private String authorImgUrl;
    private String teamImgUrl;
    private List<String> imgs;
    private String content;
    private Integer likeNumber;
    private Integer commentNumber;
    private String createdAt;
    private String type;

    @Builder
    public CreateCommentRes(String threadId, Boolean isComment, String rootThreadIdx, String authorName, String teamName, String authorImgUrl, String teamImgUrl, List<String> imgs, String content, Integer likeNumber, Integer commentNumber, String createdAt, String type) {
        this.threadId = threadId;
        this.isComment = isComment;
        this.rootThreadIdx = rootThreadIdx;
        this.authorName = authorName;
        this.teamName = teamName;
        this.authorImgUrl = authorImgUrl;
        this.teamImgUrl = teamImgUrl;
        this.imgs = imgs;
        this.content = content;
        this.likeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.createdAt = createdAt;
        this.type = type;
    }
}
