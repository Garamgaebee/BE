package com.garamgaebee.notification.service.domain.mapper;

import com.garamgaebee.notification.service.domain.dto.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.Notification;

public class NotificationMapper {

    public GetNotificationResponse NotificationToGetNotificationResponse(Notification notification) {
        return GetNotificationResponse.builder()
                .id(notification.getId())
                .event(notification.getPushSetting().getIsPushNewFunctionEvent())
                .community(notification.getPushSetting().getIsPushTeamEvent())
                .posting(notification.getPushSetting().getIsPushThreadEvent())
                .hotPosting(notification.getPushSetting().getIsPushHotThreadEvent())
                .build();
    }
}
