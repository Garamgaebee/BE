package com.garamgaebee.notification.service.domain.port.output.persistence;

import com.garamgaebee.notification.service.domain.entity.Notification;

public interface SaveNotificationPort {
    public Notification createNotification(Notification notification);
}
