package com.garamgaebee.thread.domain.mapper;

import com.garamgaebee.thread.domain.dto.GetCommentRes;
import com.garamgaebee.thread.domain.dto.GetThreadListRes;
import com.garamgaebee.thread.domain.entity.Thread;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThreadDomainMapper {
    public List<GetCommentRes> commentListToDtoList(List<Thread> commentList) {

        List<GetCommentRes> resList = new ArrayList<>();

        for (Thread thread : commentList) {
            resList.add(commentToDto(thread));
        }

        return resList;
    }

    private GetCommentRes commentToDto(Thread comment){
        return GetCommentRes.builder()
                .authorIdx(comment.getAuthorIdx())
                .commentId(comment.getThreadIdx())
                .rootThreadId(comment.getParentIdx())
                .authorImgUrl(comment.getMemberProfileImg())
                .teamImgUrl(comment.getTeamProfileImg())
                .authorName(comment.getAuthorName())
                .teamName(comment.getTeamName())
                .imgs(comment.getImgUrls())
                .content(comment.getContent())
                .likeNumber(comment.getLikeNumber())
                .createdAt(comment.getCreatedAt().toString())
                .isComment(Boolean.TRUE)
                .type(comment.getType().toString())
                .build();
    }

    public GetThreadListRes ThreadListToDtoList(Thread thread) {
        return GetThreadListRes.builder()
                .isComment(thread.isComment())
                .authorIdx(thread.getAuthorIdx())
                .threadId(thread.getThreadIdx())
                .rootThreadId(thread.getParentIdx())
                .authorImgUrl(thread.getMemberProfileImg())
                .teamImgUrl(thread.getTeamProfileImg())
                .authorName(thread.getAuthorName())
                .teamName(thread.getTeamName())
                .imgs(thread.getImgUrls())
                .content(thread.getContent())
                .likeNumber(thread.getLikeNumber())
                .commentNumber(thread.getCommentNumber())
                .createdAt(thread.getCreatedAt().toString())
                .type(thread.getType().toString())
                .build();
    }
}
