package com.garamgaebee.thread.application.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/threads")
public class ThreadController {
    /**
     * 스레드 생성 API
     * [POST] ~/api/threads
     * */

    /**
     * 스레드 수정 API
     * [PATCH] ~/api/threads
     * */

    /**
     * 스레드 삭제 API
     * [DELETE] ~/api/threads
     * */

    /**
     * 스레드 상세 조회 API
     * [GET] ~/api/threads
     * 최신순 인기순 타입별로 정렬 방식이 달라야 함
     * */

    /**
     * 스레드 메인 리스트 조회
     * [GET] ~/api/threads/list
     * */

    /**
     * 스레드 팀 리스트 조회
     * [GET] ~/api/threads/list/team
     * */

    /**
     * 스레드 좋아요 생성
     * [POST] ~/api/threads/like
     * */

    /**
     * 스레드 좋아요 삭제
     * [DELETE] ~/api/threads/like
     * */

    /**
     * 댓글 생성
     * [POST] ~/api/threads/reply
     * */

    /**
     * 댓글 수정
     * [PATCH] ~/api/threads/reply
     * */

    /**
     * 댓글 삭제
     * [DELETE] ~api/threads/reply
     * */

    /**
     * 댓글 리스트 조회
     * [GET] ~/api/threads/reply/list
     * */

    /**
     * 댓글 좋아요 생성
     * [POST] ~/api/threads/reply/list
     * */

    /**
     * 댓글 좋아요 삭제
     * [DELETE] ~/api/threads/reply/like
     * */
}
