package com.garamgaebee.teamapplicationservice.ports.input;

import com.garamgaebee.teamapplicationservice.dto.command.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.command.DoneTeamCommand;
import com.garamgaebee.teamapplicationservice.dto.command.ExitTeamCommand;
import com.garamgaebee.teamapplicationservice.dto.response.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.command.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.response.DoneTeamResponse;
import com.garamgaebee.teamapplicationservice.dto.response.ExitTeamResponse;
import com.garamgaebee.teamapplicationservice.dto.response.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;

import java.util.UUID;

public interface TeamApplicationService {
    GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand);

    CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand);

    GetFeignTeamResponse getFeignTeam(UUID teamId);

    DoneTeamResponse doneTeam(DoneTeamCommand doneTeamCommand);

    ExitTeamResponse exitTeam(ExitTeamCommand exitTeamCommand);
}
