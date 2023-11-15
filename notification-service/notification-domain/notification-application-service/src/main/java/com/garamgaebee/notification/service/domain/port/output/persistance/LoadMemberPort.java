package com.garamgaebee.notification.service.domain.port.output.persistance;

import com.garamgaebee.notification.service.domain.entity.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadMemberPort {
    public Optional<Member> loadMemberByOwner(UUID ownerId);
    public List<Member> loadAllMembers();

    public List<Member> loadMembersByOwners(List<UUID> ownerId);
}
