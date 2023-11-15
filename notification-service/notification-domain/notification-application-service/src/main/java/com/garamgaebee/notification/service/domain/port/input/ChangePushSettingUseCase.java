package com.garamgaebee.notification.service.domain.port.input;

import java.util.UUID;

public interface ChangePushSettingUseCase {
    // 이벤트 및 기능 추가 알림 설정 변경
    public Boolean changePushNewEventNotificationStatus(UUID ownerId);
    // Team 관련 알림 설정 변경
    public Boolean changePushTeamEventNotificationStatus(UUID ownerId);
    // Thread 관련 알림 설정 변경
    public Boolean changePushThreadEventNotificationStatus(UUID ownerId);
    // 인기 Thread 관련 알림 설정 변경
    public Boolean changePushHotThreadEventNotificationStatus(UUID ownerId);
}
