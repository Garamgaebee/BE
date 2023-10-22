package com.garamgaebee.notification.service.domain;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.dto.CreateNotificationCommand;
import com.garamgaebee.notification.service.domain.dto.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.mapper.NotificationMapper;
import com.garamgaebee.notification.service.domain.port.input.service.NotificationApplicationService;
import com.garamgaebee.notification.service.domain.port.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;

    @Override
    public GetNotificationResponse getNotificationSettingInfo(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        return notificationMapper.NotificationToGetNotificationResponse(notification);
    }

    @Override
    public Boolean changePushNewEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushNewFunctionEventStatus();
        return true;
    }

    @Override
    public Boolean changePushTeamEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushTeamEventStatus();
        return true;
    }

    @Override
    public Boolean changePushThreadEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushThreadEventStatus();
        return true;
    }

    @Override
    public Boolean changePushHotThreadEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushHotThreadEventStatus();
        return true;
    }

    @Override
    public void createNewNotification(CreateNotificationCommand createNotificationCommand) {

    }
}
