package com.garamgaebee.thread.application.rest;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.common.exception.ErrorDTO;
import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.thread.domain.dto.*;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
     */
    @Operation(summary = "스레드 생성 API(개인)", description = "개인 자격의 스레드 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateThreadRes.class))),
            @ApiResponse(responseCode = "400", description = "내용 길이 초과",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CreateThreadRes>> createThread(
            @Parameter(
                    description = "multipart/form-data 형식의 이미지 파일 리스트 (하나라도 리스트로 해서 보내주세요)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("file") List<MultipartFile> fileList,
            @Parameter(
                    description = "스레드 데이터 (Try it out 누르셔야 상세 값이 나옵니다)",
                    content = @Content(schema = @Schema(implementation = CreateThreadCommand.class))
            )
            @RequestPart("meta") CreateThreadCommand req) throws BaseException {
        log.info("debug");
        //todo: domain-core로 옮기기
        if (req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateThreadRes createdThread = threadService.createThread(fileList, req);
        return ResponseEntity.ok(new BaseResponse<>(createdThread));
    }

    /**
     * 스레드 생성 API (팀)
     * [POST] ~/api/threads/{team-id}
     */
    @Operation(summary = "스레드 생성 API(팀)", description = "팀 자격의 스레드 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateThreadRes.class))),
            @ApiResponse(responseCode = "400", description = "내용 길이 초과",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "/{team-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CreateThreadRes>> createTeamThread(
            @Parameter(
                    description = "multipart/form-data 형식의 이미지 파일 리스트 (하나라도 리스트로 해서 보내주세요)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("file") List<MultipartFile> fileList,
            @Parameter(
                    description = "스레드 데이터 (Try it out 누르셔야 상세 값이 나옵니다)",
                    content = @Content(schema = @Schema(implementation = CreateThreadCommand.class))
            )
            @RequestPart("meta") CreateThreadCommand req,
            @Parameter(
                    description = "팀 Id"
            )
            @PathVariable("team-id") Long teamId) {
        //todo: domain-core로 옮기기
        if (req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateThreadRes createdThread = threadService.createTeamThread(fileList, req, teamId);

        return ResponseEntity.ok(new BaseResponse<>(createdThread));
    }

    /**
     * 스레드, 댓글 삭제 API
     * [DELETE] ~/api/threads/{thread-id}
     */
    @Operation(summary = "스레드, 댓글 삭제 API", description = "댓글과 게시글(스레드) 공용 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DeleteThreadRes.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "404", description = "대상 스레드를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @DeleteMapping(value = "/{thread-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<DeleteThreadRes>> deleteThread(@Parameter(description = "삭제 대상 id (UUID String)") @PathVariable("thread-id") String threadIdx) {
        DeleteThreadRes res = threadService.deleteThread(threadIdx);

        return ResponseEntity.ok(new BaseResponse<>(res));
    }

    /**
     * 스레드 메인 리스트 조회
     * [GET] ~/api/threads
     * 최신순 인기순 타입별로 정렬 방식이 달라야 함
     */
    @Operation(summary = "스레드 메인 리스트 조회 API", description = "스레드 메인 리스트 조회 최신순 인기순 별로 정렬 방식이 달라야 함")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "하기 객체의 리스트를 리턴합니다.",
                    content = @Content(schema = @Schema(implementation = GetThreadListRes.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<GetThreadListRes>>> getThreadList(@Parameter(description = "0이면 최신순, 1이면 인기순") @RequestParam("order-type") Integer orderType) {
        List<GetThreadListRes> res = threadService.getThreadList(orderType);

        return ResponseEntity.ok(new BaseResponse<>(res));
    }

    /**
     * 스레드 팀 리스트 조회
     * [GET] ~/api/threads/team
     */
    @Operation(summary = "스레드 팀 리스트 조회 API", description = "스레드 팀 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "하기 객체의 리스트를 리턴합니다.",
                    content = @Content(schema = @Schema(implementation = GetThreadListRes.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "404", description = "해당 팀에 스레드가 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping(value = "/team", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<GetThreadListRes>>> getThreadTeamList(@RequestParam("team-id") Long teamIdx) {
        List<GetThreadListRes> res = threadService.getThreadTeamList(teamIdx);

        return ResponseEntity.ok(new BaseResponse<>(res));
    }

    /**
     * 댓글 생성 (개인)
     * [POST] ~/api/threads/reply
     */
    @Operation(summary = "댓글 생성 API(개인)", description = "개인 자격의 댓글 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateCommentRes.class))),
            @ApiResponse(responseCode = "400", description = "내용 길이 초과",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "대상 스레드를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "/reply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CreateCommentRes>> createComment(
            @Parameter(
                    description = "multipart/form-data 형식의 이미지 파일 리스트 (하나라도 리스트로 해서 보내주세요)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("file") @Nullable List<MultipartFile> fileList,
            @Parameter(
                    description = "댓글 데이터 (Try it out 누르셔야 상세 값이 나옵니다)",
                    content = @Content(schema = @Schema(implementation = CreateCommentCommand.class))
            )
            @RequestPart("meta") CreateCommentCommand req) throws BaseException {
        //todo: domain-core로 옮기기
        if (req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateCommentRes createdThread = threadService.createComment(fileList, req);

        return ResponseEntity.ok(new BaseResponse<>(createdThread));
    }

    /**
     * 댓글 생성 (팀)
     * [POST] ~/api/threads/reply/{team-idx}
     */
    @Operation(summary = "댓글 생성 API(팀)", description = "팀 자격의 댓글 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateCommentRes.class))),
            @ApiResponse(responseCode = "400", description = "내용 길이 초과",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "대상 스레드를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "reply/{team-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CreateCommentRes>> createTeamThread(
            @Parameter(
                    description = "multipart/form-data 형식의 이미지 파일 리스트 (하나라도 리스트로 해서 보내주세요)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("file") List<MultipartFile> fileList,
            @Parameter(
                    description = "댓글 데이터 (Try it out 누르셔야 상세 값이 나옵니다)",
                    content = @Content(schema = @Schema(implementation = CreateCommentCommand.class))
            )
            @RequestPart("meta") CreateCommentCommand req, @Parameter(description = "팀 id") @PathVariable("team-id") Long teamId) {
        //todo: domain-core로 옮기기
        if (req.getContent().getBytes().length > 500) throw new BaseException(BaseErrorCode.CONTENT_TOO_LONG);

        CreateCommentRes createdThread = threadService.createTeamComment(fileList, req, teamId);

        return ResponseEntity.ok(new BaseResponse<>(createdThread));
    }

    /**
     * 댓글 리스트 조회
     * [GET] ~/api/threads/reply/list
     */
    @Operation(summary = "댓글 리스트 조회", description = "댓글 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "하기 객체를 리스트로 리턴",
                    content = @Content(schema = @Schema(implementation = GetCommentRes.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "404", description = "댓글이 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @GetMapping(value = "/reply/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<GetCommentRes>>> getCommentList(@RequestParam("thread-id") String threadIdx) {
        List<GetCommentRes> resList = threadService.getCommentList(threadIdx);

        return ResponseEntity.ok(new BaseResponse<>(resList));
    }

    /**
     * 스레드, 댓글 좋아요 생성
     * [POST] ~/api/threads/like
     */
    @Operation(summary = "스레드, 댓글 좋아요 생성 API", description = "댓글과 게시글(스레드) 통합 좋아요 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateLikeRes.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "/like", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CreateLikeRes>> createLike(@Parameter(schema = @Schema(implementation = CreateLikeCommand.class)) @RequestBody CreateLikeCommand req) {
        CreateLikeRes createdLike = threadService.createLike(req);

        return ResponseEntity.ok(new BaseResponse<>(createdLike));
    }

    /**
     * 스레드, 댓글 좋아요 삭제
     * [DELETE] ~/api/threads/like
     */
    @Operation(summary = "스레드, 댓글 좋아요 삭제 API", description = "댓글과 게시글(스레드) 통합 좋아요 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DeleteLikeRes.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @DeleteMapping("/like")
    public ResponseEntity<BaseResponse<DeleteLikeRes>> deleteLike(@Parameter(schema = @Schema(implementation = DeleteLikeCommand.class)) @RequestBody DeleteLikeCommand req) {
        DeleteLikeRes deleteLike = threadService.deleteLike(req);

        return ResponseEntity.ok(new BaseResponse<>(deleteLike));
    }

}
