package com.garamgaebee.notification.service.domain.mapper;

import com.garamgaebee.notification.service.domain.dto.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.Notification;

public class NotificationMapper {

    public GetNotificationResponse NotificationToGetNotificationResponse(Notification notification) {
        return GetNotificationResponse.builder()
                .id(notification.getId())
                .event(notification.getNotificationSetting().getIsPushNewFunctionEvent())
                .community(notification.getNotificationSetting().getIsPushTeamEvent())
                .posting(notification.getNotificationSetting().getIsPushThreadEvent())
                .hotPosting(notification.getNotificationSetting().getIsPushHotThreadEvent())
                .build();
    }
}
