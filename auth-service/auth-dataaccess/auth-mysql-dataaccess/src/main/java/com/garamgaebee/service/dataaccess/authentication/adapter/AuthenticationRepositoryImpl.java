package com.garamgaebee.service.dataaccess.authentication.adapter;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.entity.Oauth2Authentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.CommonAuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.Oauth2AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.mapper.AuthenticationDataAccessMapper;
import com.garamgaebee.service.dataaccess.authentication.repository.AuthenticationJpaRepository;
import com.garamgaebee.service.dataaccess.authentication.repository.CommonAuthenticationRepository;
import com.garamgaebee.service.dataaccess.authentication.repository.Oauth2AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final AuthenticationJpaRepository authenticationJpaRepository;
    private final Oauth2AuthenticationRepository oauth2AuthenticationRepository;
    private final CommonAuthenticationRepository commonAuthenticationRepository;
    private final AuthenticationDataAccessMapper authenticationDataAccessMapper;


    @Override
    public Optional<Oauth2Authentication> findOauth2AuthenticationByOauthId(String oauthId) {
        Optional<Oauth2AuthenticationEntity> oauth2AuthenticationEntity = oauth2AuthenticationRepository.findByOauthId(oauthId);

        if(oauth2AuthenticationEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authenticationDataAccessMapper.oauth2AuthenticationEntityToOauth2Authentication(oauth2AuthenticationEntity.get()));
    }

    @Override
    public Optional<CommonAuthentication> findCommonAuthenticationByEmail(String email) {
        Optional<CommonAuthenticationEntity> commonAuthenticationEntity = commonAuthenticationRepository.findByEmail(email);

        if(commonAuthenticationEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authenticationDataAccessMapper.commonAuthenticationEntityToCommonAuthentication(commonAuthenticationEntity.get()));
    }

    @Override
    public Optional<Authentication> findAuthenticationByMemberId(UUID memberId) {
        Optional<AuthenticationEntity> authenticationEntity = authenticationJpaRepository.findByMemberId(memberId);

        if(authenticationEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authenticationDataAccessMapper.authenticationEntityToAuthentication(authenticationEntity.get()));
    }

    @Override
    public Oauth2Authentication persistOauth2Authentication(Oauth2Authentication oauth2Authentication) {
        return authenticationDataAccessMapper.oauth2AuthenticationEntityToOauth2Authentication(
                oauth2AuthenticationRepository.save(
                        authenticationDataAccessMapper.oauth2AuthenticationToOauth2AuthenticationEntity(oauth2Authentication)
            )
        );
    }

    @Override
    public CommonAuthentication persistCommonAuthentication(CommonAuthentication commonAuthentication) {
        return authenticationDataAccessMapper.commonAuthenticationEntityToCommonAuthentication(
                commonAuthenticationRepository.save(
                        authenticationDataAccessMapper.commonAuthenticationToCommonAuthenticationEntity(commonAuthentication)
                )
        );
    }

    @Override
    public Boolean checkEmailExist(String email) {
        return authenticationJpaRepository.existsByEmail(email);
    }
}
