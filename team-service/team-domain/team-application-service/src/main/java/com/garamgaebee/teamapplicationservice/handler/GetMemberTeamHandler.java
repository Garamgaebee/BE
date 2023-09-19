package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.command.GetMemberTeamCommand;
import com.garamgaebee.teamapplicationservice.dto.response.GetMemberTeam;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetMemberTeamHandler {
    private final TeamRepository teamRepository;
    private final TeamDataMapper teamDataMapper;

    public List<GetMemberTeam> findMemberTeamList(GetMemberTeamCommand getMemberTeamCommand) {
        Member member = new Member(new MemberId(getMemberTeamCommand.getMemberId()));
        List<Team> teamList= teamRepository.findTeamListByMemberId(member);
        return teamDataMapper.teamListToGetMemberTeamList(teamList);
    }
}
