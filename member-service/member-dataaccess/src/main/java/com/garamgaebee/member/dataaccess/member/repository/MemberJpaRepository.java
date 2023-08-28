package com.garamgaebee.member.dataaccess.member.repository;

import com.garamgaebee.member.dataaccess.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    boolean existsByNicknameIgnoreCase(String nickname);
}
