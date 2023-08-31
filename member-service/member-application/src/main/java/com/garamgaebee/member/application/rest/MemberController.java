package com.garamgaebee.member.application.rest;


import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.common.exception.ErrorDTO;
import com.garamgaebee.member.domain.dto.DeleteMemberResponse;
import com.garamgaebee.member.domain.dto.GetMemberResponse;
import com.garamgaebee.member.domain.dto.ProfileImgResponse;
import com.garamgaebee.member.domain.ports.in.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 멤버 조회
     * */
    @Operation(summary = "멤버 조회 API", description = "멤버 정보를 조회하는 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetMemberResponse.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @GetMapping(value = "/{member-idx}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetMemberResponse> getMember(@Parameter(description = "멤버 인덱스 (UUID String)") @PathVariable("member-idx") String memberIdx) throws BaseException {
        GetMemberResponse getMemberResponse = memberService.getMember(UUID.fromString(memberIdx));

        return ResponseEntity.ok(getMemberResponse);
    }

    /**
     * 프로필 이미지 등록
     * */
    @Operation(summary = "프로필 이미지 등록 API", description = "프로필 이미지 등록 API (최초 업로드시 사용)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetMemberResponse.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "/{member-idx}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileImgResponse> postProfileImg(@PathVariable("member-idx") String memberIdx, @RequestPart(value = "file") MultipartFile file) {
        ProfileImgResponse profileImgResponse = memberService.postProfileImg(memberIdx, file);

        return ResponseEntity.ok(profileImgResponse);
    }

    /**
     * 프로필 이미지 수정
     * */
    @Operation(summary = "프로필 이미지 수정 API", description = "프로필 이미지 수정 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetMemberResponse.class))),
            @ApiResponse(responseCode = "400", description = "에러 없음"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error! | 기존 이미지 삭제에 실패하였습니다. 서버에 문의해주세요",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "/{member-idx}/image/modify", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileImgResponse> patchProfileImg(@PathVariable("member-idx") String memberIdx, @RequestPart(value = "file") MultipartFile file) throws BaseException{
        ProfileImgResponse profileImgResponse = memberService.patchProfileImg(memberIdx, file);

        return ResponseEntity.ok(profileImgResponse);
    }
}
