package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamFeign;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.exception.TeamNotFoundException;
import com.garamgaebee.teamdomainservice.valueobject.Size;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetMainPageHandler {
    private final TeamRepository teamRepository;
    private final TeamDataMapper teamDataMapper;
    private final TeamFeign teamFeign;
    private final TeamDomainService teamDomainService;
    public GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand) {
        Optional<Team> teamOptional = teamRepository.findByTeamId(new TeamId(getMainPageCommand.getTeamId()));
        if(teamOptional.isEmpty()){
            log.warn("Could not find team with team id:{}",getMainPageCommand.getTeamId());
            throw new TeamNotFoundException(BaseErrorCode.NOT_FOUND_TEAM);
        }
//        List<UUID> memberIdList = teamRepository.findMemberIdListByTeamId(new TeamId(getMainPageCommand.getTeamId()));
//        List<Member> member = teamFeign.mainPageMemberFindById(memberIdList);
//        List<Thread> threadList = teamFeign.mainPageThreadFindByIdOrderByDateDesc(teamOptional.get().getId().getValue());
//        List<Member> threadMemberList = teamFeign.mainPageMemberFindById(teamDomainService.getThreadMemberListbyThreadList(threadList));
//        teamDomainService.setTeam(teamOptional.get(),member,threadList,threadMemberList);
        teamOptional.get().setSize(new Size(0,0,0,0));
        log.info(teamOptional.get().toString());
        return teamDataMapper.teamToTeamMainPage(teamOptional.get());
    }
}