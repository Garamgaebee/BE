package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeignHandler {
    private final TeamRepository teamRepository;
    private final TeamDataMapper teamDataMapper;
    public GetFeignTeamResponse getFeignTeam(UUID teamId) {
        Team team = teamRepository.findAllByTeamId(new TeamId(teamId));
        return teamDataMapper.teamToFeignTeamResponse(team);
    }
}
