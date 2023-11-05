package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.vo.PushSetting;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberNotificationEntity> memberNotificationEntityList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NotificationFcmTokenEntity> notificationFcmTokenEntityList = new ArrayList<>();

    public void setPushSetting(PushSetting pushSetting) {
        setIsPushNewFunctionEvent(pushSetting.getIsPushNewFunctionEvent());
        setIsPushTeamEvent(pushSetting.getIsPushTeamEvent());
        setIsPushThreadEvent(pushSetting.getIsPushThreadEvent());
        setIsPushHotThreadEvent(pushSetting.getIsPushHotThreadEvent());
    }

    // 연관관계 메서드
    public void addNotificationFcmTokenEntity(NotificationFcmTokenEntity notificationFcmTokenEntity) {
        if(!notificationFcmTokenEntityList.contains(notificationFcmTokenEntity)) {
            notificationFcmTokenEntityList.add(notificationFcmTokenEntity);
        }

        notificationFcmTokenEntity.setNotification(this);
    }

    // 연관관계 메서드
    public void addMemberNotificationEntity(MemberNotificationEntity memberNotificationEntity) {
        if(!memberNotificationEntityList.contains(memberNotificationEntity)) {
            memberNotificationEntityList.add(memberNotificationEntity);
        }

        memberNotificationEntity.setNotification(this);
    }
}
