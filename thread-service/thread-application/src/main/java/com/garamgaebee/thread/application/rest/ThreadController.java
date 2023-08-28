package com.garamgaebee.thread.application.rest;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.thread.domain.dto.*;
import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import com.garamgaebee.thread.domain.valueobject.MemberVO;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/threads")
public class ThreadController {

    private final ThreadService threadService;

    @Autowired
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    /**
     * 스레드 생성 API (개인)
     * [POST] ~/api/threads
     * */
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateThreadRes> createThread(@RequestPart("file") List<MultipartFile> fileList, @RequestPart("meta") CreateThreadCommand req) throws BaseException {
        log.info("debug");
        if(req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateThreadRes createdThread = threadService.createThread(fileList, req);

        return ResponseEntity.ok(createdThread);
    }

    /**
     * 스레드 생성 API (팀)
     * [POST] ~/api/threads/{team-id}
     * */
    @PostMapping(value = "/{team-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateThreadRes> createTeamThread(@RequestPart("file") List<MultipartFile> fileList, @RequestPart("meta") CreateThreadCommand req, @PathVariable("team-id") Long teamId) {
        if(req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateThreadRes createdThread = threadService.createTeamThread(fileList, req, teamId);

        return ResponseEntity.ok(createdThread);
    }

    /**
     * 스레드, 댓글 삭제 API
     * [DELETE] ~/api/threads/{thread-id}
     * */
    @DeleteMapping("/{thread-id}")
    public ResponseEntity<DeleteThreadRes> deleteThread(@PathVariable("thread-id") String threadIdx) {
        DeleteThreadRes res = threadService.deleteThread(threadIdx);

        return ResponseEntity.ok(res);
    }

    /**
     * 스레드 메인 리스트 조회
     * [GET] ~/api/threads
     * 최신순 인기순 타입별로 정렬 방식이 달라야 함
     * */
    @GetMapping("")
    public ResponseEntity<List<GetThreadListRes>> getThreadList(@RequestParam("order-type") Integer orderType) {
        List<GetThreadListRes> res = threadService.getThreadList(orderType);

        return ResponseEntity.ok(res);
    }

    /**
     * 스레드 팀 리스트 조회
     * [GET] ~/api/threads/team
     * */
    @GetMapping("/team")
    public ResponseEntity<List<GetThreadListRes>> getThreadTeamList(@RequestParam("team-id") Long teamIdx){
        List<GetThreadListRes> res = threadService.getThreadTeamList(teamIdx);

        return ResponseEntity.ok(res);
    }

    /**
     * 댓글 생성 (개인)
     * [POST] ~/api/threads/reply
     * */
    @PostMapping(value = "/reply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCommentRes> createComment(@RequestPart("file") @Nullable List<MultipartFile> fileList, @RequestPart("meta") CreateCommentCommand req) throws BaseException {
        if(req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateCommentRes createdThread = threadService.createComment(fileList, req);

        return ResponseEntity.ok(createdThread);
    }

    /**
     * 댓글 생성 (팀)
     * [POST] ~/api/threads/reply/{team-idx}
     * */
    @PostMapping(value = "reply/{team-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCommentRes> createTeamThread(@RequestPart("file") List<MultipartFile> fileList, @RequestPart("meta") CreateCommentCommand req, @PathVariable("team-id") Long teamId) {
        if(req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateCommentRes createdThread = threadService.createTeamComment(fileList, req, teamId);

        return ResponseEntity.ok(createdThread);
    }

    /**
     * 댓글 리스트 조회
     * [GET] ~/api/threads/reply/list
     * */
    @GetMapping("/reply/list")
    public ResponseEntity<List<GetCommentRes>> getCommentList(@RequestParam("thread-id") String threadIdx) {
        List<GetCommentRes> resList = threadService.getCommentList(threadIdx);


        return ResponseEntity.ok(resList);
    }

    /**
     * 스레드, 댓글 좋아요 생성
     * [POST] ~/api/threads/like
     * */
    @PostMapping("/like")
    public ResponseEntity<CreateLikeRes> createLike(@RequestBody CreateLikeCommand req) {
        CreateLikeRes createdLike = threadService.createLike(req);

        return ResponseEntity.ok(createdLike);
    }

    /**
     * 스레드 좋아요, 댓글 삭제
     * [DELETE] ~/api/threads/like
     * */
    @DeleteMapping("/like")
    public ResponseEntity<DeleteLikeRes> deleteLike(@RequestBody DeleteLikeCommand req) {
        DeleteLikeRes deleteLike = threadService.deleteLike(req);

        return ResponseEntity.ok(deleteLike);
    }

}
