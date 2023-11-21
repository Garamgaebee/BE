package com.garamgaebee.service.dataaccess.notification.repository;

import com.garamgaebee.service.dataaccess.notification.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    public Optional<MemberEntity> findByOwnerId(UUID ownerId);

    public void deleteByOwnerId(UUID ownerId);

    @Modifying
    @Query("select m from MemberEntity m where m.ownerId in :ownerIdList")
    public List<MemberEntity> findByOwnerIdList(@Param("ownerIdList") List<UUID> ownerIdList);
}
