package com.garamgaebee.service.dataaccess.notification.mapper;

import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.vo.PushSetting;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NotificationDataAccessMapper {

    public Notification notificationEntityToNotificationWithoutDetailListAndFcmTokenList(NotificationEntity notificationEntity) {
        return Notification.builder()
                .id(notificationEntity.getId())
                .memberId(notificationEntity.getMemberId())
                .pushSetting(PushSetting.builder()
                        .isPushNewFunctionEvent(notificationEntity.getIsPushNewFunctionEvent())
                        .isPushTeamEvent(notificationEntity.getIsPushTeamEvent())
                        .isPushThreadEvent(notificationEntity.getIsPushThreadEvent())
                        .isPushHotThreadEvent(notificationEntity.getIsPushHotThreadEvent())
                        .build())
                .build();
    }

    public Notification notificationEntityToNotificationWithoutDetailList(NotificationEntity notificationEntity) {
        return Notification.builder()
                .id(notificationEntity.getId())
                .memberId(notificationEntity.getMemberId())
                .pushSetting(PushSetting.builder()
                        .isPushNewFunctionEvent(notificationEntity.getIsPushNewFunctionEvent())
                        .isPushTeamEvent(notificationEntity.getIsPushTeamEvent())
                        .isPushThreadEvent(notificationEntity.getIsPushThreadEvent())
                        .isPushHotThreadEvent(notificationEntity.getIsPushHotThreadEvent())
                        .build())
                .fcmTokenList(notificationEntity.getNotificationFcmTokenEntityList().stream().map(notificationFcmTokenEntity -> {
                    return FcmToken.builder()
                            .id(notificationFcmTokenEntity.getId())
                            .fcmToken(notificationFcmTokenEntity.getFcmToken())
                            .time(notificationFcmTokenEntity.getTime())
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }
}
