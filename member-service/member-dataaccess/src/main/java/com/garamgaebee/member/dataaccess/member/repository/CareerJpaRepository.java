package com.garamgaebee.member.dataaccess.member.repository;

import com.garamgaebee.member.dataaccess.member.entity.CareerEntity;
import com.garamgaebee.member.dataaccess.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CareerJpaRepository extends JpaRepository<CareerEntity, UUID> {
    @Query("select c from CareerEntity c where c.memberIdx = ?1")
    Optional<List<CareerEntity>> findCareerEntitiesByMemberIdx(MemberEntity memberIdx);
}
