package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.command.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.response.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamFeign;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.ExternalLink;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teamdomainservice.valueobject.Size;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
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
        Team team = teamRepository.findByTeamId(new TeamId(getMainPageCommand.getTeamId()));
        Position memberPosition = teamRepository.findMemberPositionByTeamId(getMainPageCommand.getTeamId(), getMainPageCommand.getMemberId());
        int teamSize = teamRepository.findMemberNums(getMainPageCommand.getTeamId()).intValue();
        UUID leaderId = teamRepository.findLeaderByTeamId(getMainPageCommand.getTeamId());
        Member Leader = teamFeign.findById(leaderId.toString());
        List<ExternalLink> externalLinkList = teamRepository.findTeamIdByNotificationList(getMainPageCommand.getTeamId());
        List<Notification> notificationList = teamRepository.findNotificationByTeamId(getMainPageCommand.getTeamId());
        team.setNotificationList(notificationList);
        team.setExternalLine(externalLinkList);
        return new GetMainPageResponse(team,memberPosition,teamSize,Leader);
    }
}