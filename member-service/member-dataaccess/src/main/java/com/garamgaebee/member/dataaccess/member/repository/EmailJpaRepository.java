package com.garamgaebee.member.dataaccess.member.repository;

import com.garamgaebee.member.dataaccess.member.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailJpaRepository extends JpaRepository<EmailEntity, UUID> {


    @Query("select e from EmailEntity e where e.memberIdx = ?1")
    Optional<List<EmailEntity>> findEmailEntitiesByMemberIdx(UUID memberIdx);
}
