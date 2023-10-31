package com.garamgaebee.service.dataaccess.notification.mapper;

import com.garamgaebee.notification.service.domain.entity.NotificationDetail;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationDetailDataAccessMapper {

    public NotificationDetailEntity notificationDetailToNotificationDetailEntity(NotificationDetail notificationDetail) {
        return NotificationDetailEntity.builder()
                .id(notificationDetail.getId())
                .title(notificationDetail.getTitle())
                .body(notificationDetail.getBody())
                .type(notificationDetail.getType())
                .isRead(notificationDetail.getIsRead())
                .moveTo(notificationDetail.getMoveTo())
                .time(notificationDetail.getTime())
                .build();
    }
}
