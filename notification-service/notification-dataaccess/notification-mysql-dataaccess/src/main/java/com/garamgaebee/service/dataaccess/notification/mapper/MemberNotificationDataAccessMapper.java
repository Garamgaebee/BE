package com.garamgaebee.service.dataaccess.notification.mapper;

import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import com.garamgaebee.service.dataaccess.notification.entity.MemberNotificationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberNotificationDataAccessMapper {

    private final NotificationDetailDataAccessMapper notificationDetailDataAccessMapper;
    public MemberNotification memberNotificationEntityToMemberNotification(MemberNotificationEntity memberNotificationEntity) {
        return MemberNotification.builder()
                .id(memberNotificationEntity.getId())
                .notificationDetail(notificationDetailDataAccessMapper.notificationDetailEntityToNotificationDetail(memberNotificationEntity.getNotificationDetail()))
                .isRead(memberNotificationEntity.getIsRead())
                .build();
    }

    public MemberNotification memberNotificationEntityToMemberNotificationWithoutNotificationAndNotificationDetail(MemberNotificationEntity memberNotificationEntity) {
        return MemberNotification.builder()
                .id(memberNotificationEntity.getId())
                .isRead(memberNotificationEntity.getIsRead())
                .build();
    }

}
