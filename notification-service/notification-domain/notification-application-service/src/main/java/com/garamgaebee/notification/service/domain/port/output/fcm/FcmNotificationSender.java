package com.garamgaebee.notification.service.domain.port.output.fcm;

import com.garamgaebee.notification.service.domain.dto.fcm.SendNotificationCommand;

public interface FcmNotificationSender {
    public void sendNotificationByToken(SendNotificationCommand sendNotificationCommand);
}
