package com.garamgaebee.service.dataaccess.notification.entity;

import jakarta.persistence.*;

@Entity
public class NotificationFcmTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private NotificationEntity notification;

    private String fcmToken;

}
