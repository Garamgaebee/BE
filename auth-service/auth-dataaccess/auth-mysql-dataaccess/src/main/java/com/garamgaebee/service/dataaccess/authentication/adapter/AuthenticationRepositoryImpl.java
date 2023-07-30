package com.garamgaebee.service.dataaccess.authentication.adapter;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.mapper.AuthenticationDataAccessMapper;
import com.garamgaebee.service.dataaccess.authentication.repository.AuthenticationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final AuthenticationJpaRepository authenticationJpaRepository;
    private final AuthenticationDataAccessMapper authenticationDataAccessMapper;
    @Override
    public Optional<Authentication> findAuthenticationByOauthId(String oauthId) {

        Optional<AuthenticationEntity> authenticationEntity = authenticationJpaRepository.findByOauthId(oauthId);

        if(authenticationEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authenticationDataAccessMapper.authenticationEntityToAuthentication(authenticationEntity.get()));
    }

    @Override
    public Authentication persistAuthentication(Authentication authentication) {

        return authenticationDataAccessMapper.authenticationEntityToAuthentication(
                authenticationJpaRepository.save(
                        authenticationDataAccessMapper.authenticationToAuthenticationEntity(authentication
                        ))
                );
    }
}
