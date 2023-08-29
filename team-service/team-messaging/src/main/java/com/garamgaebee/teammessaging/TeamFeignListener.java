package com.garamgaebee.teammessaging;

import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;
import com.garamgaebee.teamapplicationservice.ports.input.TeamApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/feign/teams")
public class TeamFeignListener {
    private final TeamApplicationService teamApplicationService;

    /**
     * 멤버 정보 리턴
     * @param teamId 팀 id
     * @return
     */
    @GetMapping
    public GetFeignTeamResponse getFeignTeam(@RequestParam("team-id") UUID teamId){
        return teamApplicationService.getFeignTeam(teamId);
    }
}
