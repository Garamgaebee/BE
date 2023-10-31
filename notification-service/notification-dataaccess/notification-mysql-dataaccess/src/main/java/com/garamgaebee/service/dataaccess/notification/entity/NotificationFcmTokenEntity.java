package com.garamgaebee.service.dataaccess.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class NotificationFcmTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private NotificationEntity notification;

    private String fcmToken;

    // 연관관계 메서드
    public void setNotification(NotificationEntity notification) {
        this.notification = notification;

        if(!notification.getNotificationFcmTokenEntityList().contains(this)) {
            notification.addNotificationFcmTokenEntity(this);
        }
    }
}
