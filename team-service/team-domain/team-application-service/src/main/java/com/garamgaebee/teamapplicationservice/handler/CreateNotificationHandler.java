package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.teamapplicationservice.dto.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.exception.TeamNotFoundException;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateNotificationHandler {
    private final TeamRepository teamRepository;
    private final TeamDomainService teamDomainService;
    private final TeamDataMapper teamDataMapper;
    public CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand) {
        Team team = teamRepository.findByTeamId(new TeamId(createNotificationCommand.getTeamId()));
        Notification notification = new Notification();
        teamDomainService.initNotification(notification, team);
        teamRepository.saveNotification(notification);
        return null;
    }
}
