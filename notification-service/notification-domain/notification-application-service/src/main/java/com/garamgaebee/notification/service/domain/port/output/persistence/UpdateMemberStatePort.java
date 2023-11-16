package com.garamgaebee.notification.service.domain.port.output.persistence;

import com.garamgaebee.notification.service.domain.entity.Member;

public interface UpdateMemberStatePort {
    public Member updateMemberFcmTokens(Member member);
}
