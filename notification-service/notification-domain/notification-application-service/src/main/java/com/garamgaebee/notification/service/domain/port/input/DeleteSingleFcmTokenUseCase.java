package com.garamgaebee.notification.service.domain.port.input;

import java.util.UUID;

public interface DeleteSingleFcmTokenUseCase {

    // 단일 fcm token 삭제
    public Boolean deleteSingleFcmToken(String fcmToken);
}
