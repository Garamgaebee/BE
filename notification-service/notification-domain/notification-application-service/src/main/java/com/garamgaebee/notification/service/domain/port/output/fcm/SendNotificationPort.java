package com.garamgaebee.notification.service.domain.port.output.fcm;

import com.garamgaebee.notification.service.domain.port.input.command.SendNotificationCommand;

public interface SendNotificationPort {
    public void sendNotificationByToken(SendNotificationCommand sendNotificationCommand);
}
