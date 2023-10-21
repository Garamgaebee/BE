package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NotificationDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private NotificationEntity notification;

    private String title;
    private String body;
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String moveTo;
    private Boolean isRead;
}
