package com.garamgaebee.teamdomainservice.valueobject;

import com.garamgaebee.teamdomainservice.common.valueobject.BaseId;

import java.util.UUID;

public class MemberId extends BaseId<UUID> {
    public MemberId(UUID value) {
        super(value);
    }
}
