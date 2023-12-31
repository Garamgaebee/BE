package com.garamgaebee.teamapplication.rest;

import com.garamgaebee.common.exception.ErrorDTO;
import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.teamapplicationservice.dto.command.*;
import com.garamgaebee.teamapplicationservice.dto.request.EditTeamRequest;
import com.garamgaebee.teamapplicationservice.dto.response.*;
import com.garamgaebee.teamapplicationservice.ports.input.TeamApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/teams")
public class TeamController {
    private final TeamApplicationService teamApplicationService;

    /**
     * 커뮤니티 뷰 api  //미완( 화면 나오면 마저 할게요 )
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     */
    @GetMapping(value = "/{teamId}/main")
    public ResponseEntity<BaseResponse<GetMainPageResponse>> getMainPage(@PathVariable UUID teamId, @RequestParam UUID memberId) {
        GetMainPageResponse getMainPageResponse = teamApplicationService.getMainPage(new GetMainPageCommand(teamId, memberId));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(getMainPageResponse));
    }

    /**
     * 팀 공지사항 작성 api
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     */
    @Operation(summary = "팀 공지사항 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description =
                    "팀을 찾을 수 없습니다.\t\n \t\n" +
                            "팀 멤버가 아닙니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "400", description =
                    "최대 글자 수를 초과하였습니다.\t\n \t\n" +
                            "최대 이미지 수를 초과하였습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping(value = "/{teamId}/notification", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<CreateNotificationResponse>> createNotification(@Parameter(name = "teamId", description = "team's' id", in = ParameterIn.PATH) @PathVariable UUID teamId,
                                                                                       @Parameter(name = "memberId", description = "member's' id", in = ParameterIn.QUERY) @RequestParam UUID memberId,
                                                                                       @Parameter(name = "image", description = "image file", in = ParameterIn.DEFAULT) @RequestPart(value = "image", required = false) List<MultipartFile> images,
                                                                                       @Parameter(name = "content", description = "notification's content", in = ParameterIn.DEFAULT) @RequestPart String content) {
        CreateNotificationResponse createNotificationResponse = teamApplicationService.createNotification(new CreateNotificationCommand(teamId, memberId, images, content));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(createNotificationResponse));
    }

    /**
     * 팀 종료하기 api
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     */
    @Operation(summary = "팀 종료하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description =
                    "팀을 찾을 수 없습니다.\t\n \t\n" +
                            "팀 멤버가 아닙니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "400", description =
                    "모임장이 아닙니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @DeleteMapping(value = "/{teamId}")
    public ResponseEntity<BaseResponse<DoneTeamResponse>> doneTeam(@Parameter(name = "teamId", description = "team's' id", in = ParameterIn.PATH) @PathVariable UUID teamId,
                                                                   @Parameter(name = "memberId", description = "member's' id", in = ParameterIn.QUERY) @RequestParam UUID memberId) {
        DoneTeamResponse doneTeamResponse = teamApplicationService.doneTeam(new DoneTeamCommand(teamId, memberId));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(doneTeamResponse));
    }

    /**
     * 팀 탈퇴하기 api
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     */
    @Operation(summary = "팀 종료하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description =
                    "팀을 찾을 수 없습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description =
                    "팀 멤버가 아닙니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @DeleteMapping(value = "/{teamId}/members/{memberId}")
    public ResponseEntity<BaseResponse<ExitTeamResponse>> exitTeam(@Parameter(name = "teamId", description = "team's' id", in = ParameterIn.PATH) @PathVariable UUID teamId,
                                                                   @Parameter(name = "memberId", description = "member's' id", in = ParameterIn.PATH) @PathVariable UUID memberId) {
        ExitTeamResponse exitTeamResponse = teamApplicationService.exitTeam(new ExitTeamCommand(teamId, memberId));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(exitTeamResponse));
    }

    /**
     * 팀 수정하기 api
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     */
    @Operation(summary = "팀 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description =
                    "팀을 찾을 수 없습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description =
                    "팀 이름 최대 글자 수를 초과했습니다.\t\n \t\n" +
                            "팀 소개 최대 글자 수를 초과했습니다.\t\n \t\n" +
                            " 팀 멤버가 아닙니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PutMapping(value = "/{teamId}")
    public ResponseEntity<BaseResponse<EditTeamResponse>> editTeamInfo(@Parameter(name = "teamId", description = "team's' id", in = ParameterIn.PATH) @PathVariable UUID teamId,
                                                                       @Parameter(name = "memberId", description = "member's' id", in = ParameterIn.QUERY) @RequestParam UUID memberId,
                                                                       @RequestBody EditTeamRequest editTeamRequest) {
        EditTeamResponse editTeamResponse = teamApplicationService.editTeamInfo(new EditTeamInfoCommand(teamId, memberId, editTeamRequest));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(editTeamResponse));
    }

    /**
     * 멤버가 속한 팀 리스트 조회 api
     *
     * @param memberId 멤버 id
     */
    @Operation(summary = "멤버가 속한 팀 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @GetMapping(value = "")
    public ResponseEntity<BaseResponse<List<GetMemberTeam>>> findMemberTeamList(@Parameter(name = "memberId", description = "member's' id", in = ParameterIn.QUERY) @RequestParam UUID memberId) {
        List<GetMemberTeam> getMemberTeamList = teamApplicationService.findMemberTeamList(new GetMemberTeamCommand(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(getMemberTeamList));
    }
}