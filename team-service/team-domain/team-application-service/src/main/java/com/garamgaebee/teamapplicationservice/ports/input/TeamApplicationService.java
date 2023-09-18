package com.garamgaebee.teamapplicationservice.ports.input;

import com.garamgaebee.teamapplicationservice.dto.command.*;
import com.garamgaebee.teamapplicationservice.dto.response.*;
import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;

import java.util.UUID;

public interface TeamApplicationService {
    GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand);

    CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand);

    GetFeignTeamResponse getFeignTeam(UUID teamId);

    DoneTeamResponse doneTeam(DoneTeamCommand doneTeamCommand);

    ExitTeamResponse exitTeam(ExitTeamCommand exitTeamCommand);

    EditTeamResponse editTeamInfo(EditTeamInfoCommand editTeamInfoCommand);
}
