package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.Oauth2AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Oauth2AuthenticationRepository extends JpaRepository<Oauth2AuthenticationEntity, Long> {
    public Optional<Oauth2AuthenticationEntity> findByOauthId(String oauthId);
}
