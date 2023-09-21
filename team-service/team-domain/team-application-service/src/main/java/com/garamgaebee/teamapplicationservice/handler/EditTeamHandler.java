package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.command.EditTeamInfoCommand;
import com.garamgaebee.teamapplicationservice.dto.request.EditTeamRequest;
import com.garamgaebee.teamapplicationservice.dto.response.EditTeamResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EditTeamHandler {
    private final TeamRepository teamRepository;
    private final TeamDomainService teamDomainService;
    private final TeamDataMapper teamDataMapper;
    public EditTeamResponse editTeam(EditTeamInfoCommand editTeamInfoCommand) {
        Team team = teamDataMapper.editTeamInfoCommandToTeam(editTeamInfoCommand);
        teamDomainService.validateAndInitEditTeamInfo(team);
        updateTeam(team);
        return new EditTeamResponse(true);
    }

    private void updateTeam(Team team) {
        teamRepository.editTeam(team);
    }
}
