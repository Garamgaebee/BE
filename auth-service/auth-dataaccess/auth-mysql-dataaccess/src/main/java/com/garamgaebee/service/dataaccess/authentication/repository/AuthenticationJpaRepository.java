package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthenticationJpaRepository<T extends AuthenticationEntity> extends JpaRepository<T, Long> {
    public Optional<T> findByMemberId(UUID memberId);
    public Optional<T> findByEmail(String email);
    public Boolean existsByEmail(String email);
    public void deleteByMemberId(UUID memberId);
}
