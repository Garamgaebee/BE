package com.garamgaebee.service.dataaccess.notification.mapper;

import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.entity.PushSetting;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import org.springframework.stereotype.Component;

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
}
