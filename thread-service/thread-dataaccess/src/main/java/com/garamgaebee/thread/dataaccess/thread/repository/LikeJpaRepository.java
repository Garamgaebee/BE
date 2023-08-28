package com.garamgaebee.thread.dataaccess.thread.repository;

import com.garamgaebee.thread.dataaccess.thread.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeJpaRepository extends JpaRepository<LikeEntity, UUID> {

    @Modifying
    @Query(value = "delete from LikeEntity l where l.targetThreadIdx = :uuid")
    void deleteAllByThreadId(UUID uuid);

    @Modifying
    @Query(value = "delete from LikeEntity l where l.targetThreadIdx = :targetThreadIdx and l.memberIdx = :memberIdx")
    void deleteByThreadAndMember(UUID targetThreadIdx, UUID memberIdx);
}
