package com.garamgaebee.notification.service.domain.port.output.persistence;

import com.garamgaebee.notification.service.domain.entity.MemberNotification;

public interface SaveMemberNotificationPort {

    public MemberNotification updateMemberNotification(MemberNotification memberNotification);
}
