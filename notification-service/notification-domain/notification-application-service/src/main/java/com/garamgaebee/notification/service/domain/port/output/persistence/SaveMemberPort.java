package com.garamgaebee.notification.service.domain.port.output.persistence;

import com.garamgaebee.notification.service.domain.entity.Member;

public interface SaveMemberPort {
    public Member updateMemberSetting(Member member);
    public Member createMember(Member member);

}
