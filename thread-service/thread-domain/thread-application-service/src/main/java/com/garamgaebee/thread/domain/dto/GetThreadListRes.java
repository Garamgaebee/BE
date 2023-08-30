package com.garamgaebee.thread.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetThreadListRes {

    @Schema(name = "threadId", description = "스레드 인덱스(댓글과 스레드는 같은 테이블에서 관리)", example = "UUID String")
    private String threadId;
    @Schema(name = "authorIdx", description = "작성자 인덱스, 본인이 작성한 것인지 여부 체크에 서용", example = "UUID String")
    private String authorIdx;
    //스레드이면 NONE
    @Schema(name = "rootThreadIdx", description = "댓글이 속한 게시글(스레드) 인덱스, 댓글일 경우 NONE으로 리턴", example = "NONE")
    private String rootThreadId;
    @Schema(name = "authorImgUrl", description = "작성자 프로필 이미지 url", example = "image url")
    private String authorImgUrl;
    //팀 게시물이 아니면 NONE
    @Schema(name = "teamImgUrl", description = "팀 프로필 이미지 url, 팀에 속하지 않은 경우 NONE 리턴", example = "image url")
    private String teamImgUrl;
    @Schema(name = "authorName", description = "작성자 닉네임", example = "test Nickname")
    private String authorName;
    @Schema(name = "department", description = "멤버 소속/출신 학과", example = "소프트웨어학과")
    private String department;
    @Schema(name = "teamName", description = "게시글, 댓글이 속한 팀 이름, 팀에 속하지 않은 경우 NONE 리턴", example = "Team name")
    private String teamName;
    @Schema(name = "imgs", description = "게시글에 같이 업로드 된 이미지 리스트", example = "image url String List")
    private List<String> imgs;
    @Schema(name = "content", description = "본문 내용", example = "본문 내용입니다.")
    private String content;
    @Schema(name = "likeNumber", description = "좋아요 갯수", example = "0")
    private int likeNumber;
    //댓글이면 -1
    @Schema(name = "commentNumber", description = "댓글 갯수 (댓글이면 -1)", example = "0")
    private int commentNumber;
    @Schema(name = "createdAt", description = "생성일 (작성일)", example = "timestamp")
    private String createdAt;
    @Schema(name = "isComment", description = "댓글 여부(댓글: true, 댓글이 아님: false", example = "true")
    @JsonProperty("isComment")
    private Boolean isComment;
    @Schema(name = "type", description = "개인: PERSONAL, 팀: TEAM", example = "PERSONAL")
    private String type;

    @Builder
    public GetThreadListRes(Boolean isComment, String authorIdx, String threadId, String rootThreadId, String authorImgUrl, String teamImgUrl, String authorName, String department, String teamName, List<String> imgs, String content, int likeNumber, int commentNumber, String createdAt, String type) {
        this.isComment = isComment;
        this.authorIdx = authorIdx;
        this.threadId = threadId;
        this.rootThreadId = rootThreadId;
        this.authorImgUrl = authorImgUrl;
        this.teamImgUrl = teamImgUrl;
        this.authorName = authorName;
        this.department = department;
        this.teamName = teamName;
        this.imgs = imgs;
        this.content = content;
        this.likeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.createdAt = createdAt;
        this.type = type;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }
}
