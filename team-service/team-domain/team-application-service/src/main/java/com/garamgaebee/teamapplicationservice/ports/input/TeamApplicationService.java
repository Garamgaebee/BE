package com.garamgaebee.teamapplicationservice.ports.input;

import com.garamgaebee.teamapplicationservice.dto.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;

import java.util.UUID;

public interface TeamApplicationService {
    GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand);

    CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand);

    GetFeignTeamResponse getFeignTeam(UUID teamId);
}
