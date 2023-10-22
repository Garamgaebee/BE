package com.garamgaebee.notification.service.domain.port.output.repository;

import com.garamgaebee.notification.service.domain.entity.Notification;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {
    public Optional<Notification> findNotificationPushSettingByMemberId(UUID memberId);
}
