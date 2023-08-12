package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.service.dataaccess.authentication.entity.CommonAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonAuthenticationRepository extends JpaRepository<CommonAuthenticationEntity, Long> {
    Optional<CommonAuthenticationEntity> findByEmail(String email);
}
