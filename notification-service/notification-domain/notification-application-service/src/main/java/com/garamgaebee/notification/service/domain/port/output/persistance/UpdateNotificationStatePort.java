package com.garamgaebee.notification.service.domain.port.output.persistance;

import com.garamgaebee.notification.service.domain.entity.Notification;

public interface UpdateNotificationStatePort {
    public Notification updateMemberNotifications(Notification notification);
}
