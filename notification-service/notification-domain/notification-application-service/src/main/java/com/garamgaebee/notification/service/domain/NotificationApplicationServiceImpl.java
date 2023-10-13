package com.garamgaebee.notification.service.domain;

import com.garamgaebee.notification.service.domain.dto.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.mapper.NotificationMapper;
import com.garamgaebee.notification.service.domain.port.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final NotificationMapper notificationMapper;

    @Override
    public GetNotificationResponse getNotificationSettingInfo(UUID memberId) {
        //TODO data access layer에서 가져오기
        Notification notification = new Notification();

        return notificationMapper.NotificationToGetNotificationResponse(notification);
    }

    @Override
    public Boolean changePushNewEventNotificationStatus(UUID memberId) {
        //TODO data access layer에서 가져오기
        Notification notification = new Notification();

        notification.changeIsPushNewFunctionEventStatus();
        return true;
    }

    @Override
    public Boolean changePushTeamEventNotificationStatus(UUID memberId) {
        //TODO data access layer에서 가져오기
        Notification notification = new Notification();

        notification.changeIsPushTeamEventStatus();
        return true;
    }

    @Override
    public Boolean changePushThreadEventNotificationStatus(UUID memberId) {
        //TODO data access layer에서 가져오기
        Notification notification = new Notification();

        notification.changeIsPushThreadEventStatus();
        return true;
    }

    @Override
    public Boolean changePushHotThreadEventNotificationStatus(UUID memberId) {
        //TODO data access layer에서 가져오기
        Notification notification = new Notification();

        notification.changeIsPushHotThreadEventStatus();
        return true;
    }
}
