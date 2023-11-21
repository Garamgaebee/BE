package com.garamgaebee.notification.service.domain.port.input;

import com.garamgaebee.notification.service.domain.port.input.response.GetMemberNotificationResponse;

import java.util.List;
import java.util.UUID;

public interface GetMemberNotificationListUseCase {

    // member 알림 목록 조회
    public List<GetMemberNotificationResponse> getMemberNotificationList(UUID ownerId);
}
