package com.garamgaebee.notification.service.domain.mapper;

import com.garamgaebee.notification.service.domain.port.input.response.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.Notification;
import org.springframework.stereotype.Component;

@Component
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
