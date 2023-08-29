package com.garamgaebee.teamapplication.rest;

import com.garamgaebee.teamapplicationservice.dto.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.ports.input.TeamApplicationService;
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
     * @param teamId 팀 id
     * @param memberId 멤버 id
     * @return
     */
    @GetMapping(value = "/{teamId}")
    public ResponseEntity<GetMainPageResponse> getMainPage(@PathVariable UUID teamId, @RequestParam UUID memberId){
        GetMainPageResponse getMainPageResponse = teamApplicationService.getMainPage(new GetMainPageCommand(teamId,memberId));
        return  ResponseEntity.ok(getMainPageResponse);
    }

    /**
     * 팀 공지사항 작성 api // 미완(확정되면 이어서 할게요)
     * @param teamId 팀 id
     * @param memberId 멤버 id
     * @return
     */
    @PostMapping(value="/{teamId}/notification", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateNotificationResponse> createNotification(@PathVariable UUID teamId, @RequestParam UUID memberId, @RequestPart(value = "image")List<MultipartFile> images, @RequestPart String content){
        CreateNotificationResponse createNotificationResponse = teamApplicationService.createNotification(new CreateNotificationCommand(teamId,memberId));
        return ResponseEntity.ok(createNotificationResponse);
    }
}