package com.garamgaebee.teamapplication.rest;

import com.garamgaebee.teamapplicationservice.dto.command.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.command.DoneTeamCommand;
import com.garamgaebee.teamapplicationservice.dto.response.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.command.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.response.DoneTeamResponse;
import com.garamgaebee.teamapplicationservice.dto.response.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.ports.input.TeamApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * @return
     */
    @GetMapping(value = "/{teamId}")
    public ResponseEntity<GetMainPageResponse> getMainPage(@PathVariable UUID teamId, @RequestParam UUID memberId) {
        GetMainPageResponse getMainPageResponse = teamApplicationService.getMainPage(new GetMainPageCommand(teamId, memberId));
        return ResponseEntity.ok(getMainPageResponse);
    }

    /**
     * 팀 공지사항 작성 api
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     * @return
     */
    @Operation(summary = "팀 공지사항 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description =
                    "팀을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "404", description =
                    "최대 글자 수를 초과하였습니다.\t\n \t\n" +
                            "최대 이미지 수를 초과하였습니다.")
    })
    @PostMapping(value = "/{teamId}/notification", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateNotificationResponse> createNotification(@Parameter(name = "teamId", description = "team's' id", in = ParameterIn.PATH) @PathVariable UUID teamId,
                                                                         @Parameter(name = "memberId", description = "member's' id", in = ParameterIn.QUERY) @RequestParam UUID memberId,
                                                                         @Parameter(name = "image", description = "image file", in = ParameterIn.DEFAULT) @RequestPart(value = "image", required = false) List<MultipartFile> images,
                                                                         @Parameter(name = "content", description = "notification's content", in = ParameterIn.DEFAULT) @RequestPart String content) {
        CreateNotificationResponse createNotificationResponse = teamApplicationService.createNotification(new CreateNotificationCommand(teamId, memberId, images, content));
        return ResponseEntity.ok(createNotificationResponse);
    }

    /**
     * 팀 종료하기 api
     *
     * @param teamId   팀 id
     * @param memberId 멤버 id
     * @return
     */
    @Operation(summary = "팀 종료하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description =
                    "팀을 찾을 수 없습니다.")
    })
    @DeleteMapping(value = "/{teamId}")
    public ResponseEntity<DoneTeamResponse> doneTeam(@Parameter(name = "teamId", description = "team's' id", in = ParameterIn.PATH) @PathVariable UUID teamId,
                                                     @Parameter(name = "memberId", description = "member's' id", in = ParameterIn.QUERY) @RequestParam UUID memberId) {
        DoneTeamResponse doneTeamResponse = teamApplicationService.doneTeam(new DoneTeamCommand(teamId,memberId));
        return ResponseEntity.ok(doneTeamResponse);
    }
}