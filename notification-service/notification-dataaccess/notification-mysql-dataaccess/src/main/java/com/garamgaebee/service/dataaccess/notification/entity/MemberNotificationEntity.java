package com.garamgaebee.service.dataaccess.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private NotificationEntity notification;

    @ManyToOne
    @JoinColumn(name = "notification_detail_id")
    private NotificationDetailEntity notificationDetail;

    @Builder
    public MemberNotificationEntity(NotificationEntity notification, NotificationDetailEntity notificationDetail) {
        setNotification(notification);
        setNotificationDetail(notificationDetail);
    }

    // 연관관계 메서드
    public void setNotification(NotificationEntity notificationEntity) {
        this.notification = notificationEntity;

        if(!notificationEntity.getMemberNotificationEntityList().contains(this)) {
            notificationEntity.addMemberNotificationEntity(this);
        }
    }

    // 연관관계 메서드
    public void setNotificationDetail(NotificationDetailEntity notificationDetailEntity) {
        this.notificationDetail = notificationDetailEntity;

        if(!notificationDetailEntity.getMemberNotificationEntityList().contains(this)) {
            notificationDetailEntity.addMemberNotification(this);
        }
    }
}
