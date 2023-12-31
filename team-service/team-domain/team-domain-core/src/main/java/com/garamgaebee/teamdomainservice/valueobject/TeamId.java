package com.garamgaebee.teamdomainservice.valueobject;

import com.garamgaebee.teamdomainservice.common.valueobject.BaseId;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
public class TeamId extends BaseId<UUID> {
    public TeamId(UUID value) {
        super(value);
    }
}
