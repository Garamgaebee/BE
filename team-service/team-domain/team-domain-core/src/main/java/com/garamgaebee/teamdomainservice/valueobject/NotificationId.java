package com.garamgaebee.teamdomainservice.valueobject;

import com.garamgaebee.teamdomainservice.common.valueobject.BaseId;

import java.util.UUID;

public class NotificationId extends BaseId<UUID> {
    public NotificationId(UUID value) {
        super(value);
    }
}
