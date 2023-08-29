package com.garamgaebee.teamapplicationservice;

import com.garamgaebee.teamapplicationservice.dto.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;
import com.garamgaebee.teamapplicationservice.handler.CreateNotificationHandler;
import com.garamgaebee.teamapplicationservice.handler.FeignHandler;
import com.garamgaebee.teamapplicationservice.handler.GetMainPageHandler;
import com.garamgaebee.teamapplicationservice.ports.input.TeamApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Service
@RequiredArgsConstructor
public class TeamApplicationServiceImpl implements TeamApplicationService {
    private final GetMainPageHandler getMainPageHandler;
    private final CreateNotificationHandler createNotificationHandler;
    private final FeignHandler feignHandler;
    @Override
    public GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand) {
        return getMainPageHandler.getMainPage(getMainPageCommand);
    }

    @Override
    public CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand) {
        return createNotificationHandler.createNotification(createNotificationCommand);
    }

    @Override
    public GetFeignTeamResponse getFeignTeam(UUID teamId) {
        return feignHandler.getFeignTeam(teamId);
    }
}
