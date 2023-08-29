package com.garamgaebee.teamdomainservice.valueobject;

import com.garamgaebee.teamdomainservice.common.valueobject.BaseId;

import java.util.UUID;

public class ThreadId extends BaseId<UUID> {
    protected ThreadId(UUID value) {
        super(value);
    }
}
