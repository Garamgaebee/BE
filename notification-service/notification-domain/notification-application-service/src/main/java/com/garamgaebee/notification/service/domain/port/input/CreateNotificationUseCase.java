package com.garamgaebee.notification.service.domain.port.input;

import com.garamgaebee.notification.service.domain.port.input.command.CreateHotThreadNotificationCommand;
import com.garamgaebee.notification.service.domain.port.input.command.CreateNewNotificationCommand;
import com.garamgaebee.notification.service.domain.port.input.command.CreateTeamNotificationCommand;
import com.garamgaebee.notification.service.domain.port.input.command.CreateThreadNotificationCommand;

public interface CreateNotificationUseCase {
    // 신기능 알림 생성
    public Boolean createNewNotification(CreateNewNotificationCommand createNewNotificationCommand);
    // 팀 알림 생성
    public Boolean createTeamNotification(CreateTeamNotificationCommand createTeamNotificationCommand);
    // thread 알림 생성
    public Boolean createThreadNotification(CreateThreadNotificationCommand createThreadNotificationCommand);
    // hot thread 알림 생성
    public Boolean createHotThreadNotification(CreateHotThreadNotificationCommand createHotThreadNotificationCommand);
}
