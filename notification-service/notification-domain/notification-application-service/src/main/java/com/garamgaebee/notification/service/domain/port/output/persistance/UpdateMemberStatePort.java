package com.garamgaebee.notification.service.domain.port.output.persistance;

import com.garamgaebee.notification.service.domain.entity.Member;

public interface UpdateMemberStatePort {
    public Member updateMemberFcmTokens(Member member);
}
