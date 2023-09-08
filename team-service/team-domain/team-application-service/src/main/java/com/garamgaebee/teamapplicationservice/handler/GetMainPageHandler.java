package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.command.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.response.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamFeign;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.Size;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetMainPageHandler {
    private final TeamRepository teamRepository;
    private final TeamDataMapper teamDataMapper;
    private final TeamFeign teamFeign;
    private final TeamDomainService teamDomainService;
    public GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand) {
        Team team = teamRepository.findByTeamId(new TeamId(getMainPageCommand.getTeamId()));
//        List<UUID> memberIdList = teamRepository.findMemberIdListByTeamId(new TeamId(getMainPageCommand.getTeamId()));
//        List<Member> member = teamFeign.mainPageMemberFindById(memberIdList);
//        List<Thread> threadList = teamFeign.mainPageThreadFindByIdOrderByDateDesc(teamOptional.get().getId().getValue());
//        List<Member> threadMemberList = teamFeign.mainPageMemberFindById(teamDomainService.getThreadMemberListbyThreadList(threadList));
//        teamDomainService.setTeam(teamOptional.get(),member,threadList,threadMemberList);
        team.setSize(new Size(0,0,0,0));
        return teamDataMapper.teamToTeamMainPage(team);
    }
}