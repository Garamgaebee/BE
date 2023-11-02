package com.garamgaebee.notification.service.domain.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberNotification {
    private Long id;
    private Notification notification;
    private NotificationDetail notificationDetail;
    private Boolean isRead;
}
