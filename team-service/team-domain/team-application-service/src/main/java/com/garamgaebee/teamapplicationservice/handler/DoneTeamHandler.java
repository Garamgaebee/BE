package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.teamapplicationservice.dto.command.DoneTeamCommand;
import com.garamgaebee.teamapplicationservice.dto.response.DoneTeamResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DoneTeamHandler {
    private final TeamRepository teamRepository;
    private final TeamDomainService teamDomainService;
    public DoneTeamResponse doneTeam(DoneTeamCommand doneTeamCommand) {
        Member member = new Member(new MemberId(doneTeamCommand.getMemberId()));
        Team team = new Team(new TeamId(doneTeamCommand.getTeamId()));
        teamDomainService.initMemberTeam(member,team);
        Position position = teamRepository.findMemberPositionInTeam(member);
        teamDomainService.initMemberPosition(member,position);
        if(!teamDomainService.isPossibleDoneTeam(member)){
            throw new BaseException(BaseErrorCode.TEAM_NOT_LEADER);
        }
        teamRepository.doneTeam(member);
        return new DoneTeamResponse(true);
    }
}
