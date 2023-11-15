package com.garamgaebee.notification.service.domain.port.output.persistance;

import com.garamgaebee.notification.service.domain.entity.Member;

public interface SaveMemberPort {
    public Member updateMemberSetting(Member member);
    public Member createMember(Member member);

}
