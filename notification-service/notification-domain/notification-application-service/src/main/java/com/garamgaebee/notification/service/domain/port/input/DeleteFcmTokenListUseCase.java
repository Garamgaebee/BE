package com.garamgaebee.notification.service.domain.port.input;

import java.util.List;

public interface DeleteFcmTokenListUseCase {

    // fcm token 리스트 삭제
    public void deleteFcmTokenList(List<String> fcmTokenList);
}
