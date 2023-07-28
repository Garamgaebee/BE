package com.garamgaebee.member.dataaccess.member.repository;

import com.garamgaebee.member.dataaccess.member.entity.SnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SnsJpaRepository extends JpaRepository<SnsEntity, UUID> {
    @Query("select s from SnsEntity s where s.memberIdx = ?1")
    Optional<List<SnsEntity>> findSnsEntitiesByMemberIdx(UUID memberIdx);
}
