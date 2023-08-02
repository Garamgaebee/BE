package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthenticationJpaRepository extends JpaRepository<AuthenticationEntity, Long> {

    public Optional<AuthenticationEntity> findByOauthId(String oauthId);
    public Optional<AuthenticationEntity> findByMemberId(UUID memberId);
}
