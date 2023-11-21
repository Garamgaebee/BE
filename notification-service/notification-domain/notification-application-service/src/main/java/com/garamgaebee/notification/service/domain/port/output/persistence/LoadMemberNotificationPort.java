package com.garamgaebee.notification.service.domain.port.output.persistence;

import com.garamgaebee.notification.service.domain.entity.MemberNotification;

import java.util.List;
import java.util.Optional;

public interface LoadMemberNotificationPort {

    public Optional<MemberNotification> loadMemberNotification(Long memberNotificationId);
    public List<MemberNotification> loadMemberNotificationsByMemberId(Long memberId);
}
