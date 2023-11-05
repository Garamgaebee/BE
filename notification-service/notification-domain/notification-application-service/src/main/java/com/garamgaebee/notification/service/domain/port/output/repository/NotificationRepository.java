package com.garamgaebee.notification.service.domain.port.output.repository;

import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.entity.NotificationDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {
    public List<Notification> findAllNotificationFcmToken();
    public Optional<Notification> findNotificationPushSettingByMemberId(UUID memberId);
    public Optional<Notification> findNotificationFcmTokenByMemberId(UUID memberId);
    public void changeNotificationPushSetting(Notification notification);
    public void createNewNotification(Notification notification);
    public NotificationDetail createNewNotificationDetail(NotificationDetail notificationDetail);
    public void createMemberNotification(Notification notification, NotificationDetail notificationDetail);

}
