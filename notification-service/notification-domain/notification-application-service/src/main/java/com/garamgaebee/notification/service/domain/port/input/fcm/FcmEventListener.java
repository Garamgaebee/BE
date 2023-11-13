package com.garamgaebee.notification.service.domain.port.input.fcm;

import java.util.List;

public interface FcmEventListener {
    public void deleteFcmTokenList(List<String> fcmTokenList);
}
