package com.garamgaebee.thread.domain.ports.in;

import com.garamgaebee.thread.domain.dto.*;
import com.garamgaebee.thread.domain.entity.Thread;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface ThreadService {
    //스레드 생성 (개인)
    CreateThreadRes createThread(List<MultipartFile> fileList, CreateThreadCommand req);

    //스레드 생성 (팀)
    CreateThreadRes createTeamThread(List<MultipartFile> fileList, CreateThreadCommand req, Long teamId);

    //스레드 삭제
    DeleteThreadRes deleteThread(String threadIdx);

    //댓글 생성 (개인)
    CreateCommentRes createComment(List<MultipartFile> fileList, CreateCommentCommand req);

    //댓글 생성 (팀)
    CreateCommentRes createTeamComment(List<MultipartFile> fileList, CreateCommentCommand req, Long teamId);

    //스레드, 댓글 좋아요 생성
    CreateLikeRes createLike(String threadIdx);

    //스레드, 댓글 좋아요 삭제
    DeleteLikeRes deleteLike(String threadIdx);

    // 댓글 리스트 조회
    List<GetCommentRes> getCommentList(String threadIdx);

    //메인 스레드 리스트 조회
    List<GetThreadListRes> getThreadList(Integer orderType);

    List<GetThreadListRes> getThreadTeamList(Long teamIdx);
}
