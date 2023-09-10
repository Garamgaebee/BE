package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.command.ExitTeamCommand;
import com.garamgaebee.teamapplicationservice.dto.response.ExitTeamResponse;
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
public class ExitTeamHandler {
    private final TeamRepository teamRepository;
    private final TeamDomainService teamDomainService;

    public ExitTeamResponse exitTeam(ExitTeamCommand exitTeamCommand) {
        Member member = new Member(new MemberId(exitTeamCommand.getMemberId()));
        Team team = new Team(new TeamId(exitTeamCommand.getTeamId()));
        teamDomainService.initMemberTeam(member,team);
        teamRepository.exitTeam(member);
        return new ExitTeamResponse(true);
    }
}