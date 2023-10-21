package com.garamgaebee.service.dataaccess.notification.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID memberId;
    private Boolean isPushNewFunctionEvent;
    private Boolean isPushTeamEvent;
    private Boolean isPushThreadEvent;
    private Boolean isPushHotThreadEvent;

    @Builder.Default
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NotificationDetailEntity> notificationDetailEntityList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NotificationFcmTokenEntity> notificationFcmTokenEntityList = new ArrayList<>();
}
