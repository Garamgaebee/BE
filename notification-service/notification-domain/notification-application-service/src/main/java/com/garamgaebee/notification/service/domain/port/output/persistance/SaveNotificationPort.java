package com.garamgaebee.notification.service.domain.port.output.persistance;

import com.garamgaebee.notification.service.domain.entity.Notification;

public interface SaveNotificationPort {
    public Notification createNotification(Notification notification);
}
