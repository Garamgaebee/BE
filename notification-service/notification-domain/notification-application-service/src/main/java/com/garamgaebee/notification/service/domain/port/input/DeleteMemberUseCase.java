package com.garamgaebee.notification.service.domain.port.input;

import java.util.UUID;

public interface DeleteMemberUseCase {

    // 멤버 삭제
    public Boolean deleteMember(UUID ownerId);
}
