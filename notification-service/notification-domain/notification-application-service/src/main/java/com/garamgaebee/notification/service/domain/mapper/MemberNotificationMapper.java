package com.garamgaebee.notification.service.domain.mapper;

import com.garamgaebee.notification.service.domain.dto.GetMemberNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import org.springframework.stereotype.Component;

@Component
public class MemberNotificationMapper {

    public GetMemberNotificationResponse memberNotificationToGetMemberNotificationResponse(MemberNotification memberNotification) {
        return GetMemberNotificationResponse.builder()
                .id(memberNotification.getId())
                .title(memberNotification.getNotificationDetail().getTitle())
                .body(memberNotification.getNotificationDetail().getBody())
                .time(memberNotification.getNotificationDetail().getTime())
                .type(memberNotification.getNotificationDetail().getType())
                .moveTo(memberNotification.getNotificationDetail().getMoveTo())
                .isRead(memberNotification.getIsRead())
                .build();
    }
}
