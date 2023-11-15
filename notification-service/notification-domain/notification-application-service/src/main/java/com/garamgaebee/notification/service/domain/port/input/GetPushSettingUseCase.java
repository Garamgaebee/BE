package com.garamgaebee.notification.service.domain.port.input;

import com.garamgaebee.notification.service.domain.port.input.response.GetNotificationResponse;

import java.util.UUID;

public interface GetPushSettingUseCase {

    // 알림 설정 리스트 조회
    public GetNotificationResponse getNotificationSettingInfo(UUID ownerId);
}
