package com.garamgaebee.notification.service.domain.port.output.persistence;

import java.util.UUID;

public interface DeleteMemberPort {
    public void deleteMemberByOwner(UUID ownerId);
}
