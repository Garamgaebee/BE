package com.garamgaebee.notification.service.domain.port.input.service;

import com.garamgaebee.notification.service.domain.dto.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.entity.Notification;

import java.util.UUID;

public interface NotificationApplicationService {

    // 알림 설정 리스트 조회
    public GetNotificationResponse getNotificationSettingInfo(UUID memberId);

    // 이벤트 및 기능 추가 알림 설정 변경
    public Boolean changePushNewEventNotificationStatus(UUID memberId);
    // Team 관련 알림 설정 변경
    public Boolean changePushTeamEventNotificationStatus(UUID memberId);
    // Thread 관련 알림 설정 변경
    public Boolean changePushThreadEventNotificationStatus(UUID memberId);
    // 인기 Thread 관련 알림 설정 변경
    public Boolean changePushHotThreadEventNotificationStatus(UUID memberId);

}
