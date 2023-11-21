package com.garamgaebee.notification.service.domain.port.input;

public interface ReadMemberNotificationUseCase {

    // 알림 읽음 처리
    public Boolean readMemberNotification(Long memberNotificationId);
}
