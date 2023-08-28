package com.garamgaebee.teamapplicationservice.ports.input;

import com.garamgaebee.teamapplicationservice.dto.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageCommand;
import com.garamgaebee.teamapplicationservice.dto.GetMainPageResponse;

public interface TeamApplicationService {
    GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand);

    CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand);
}
