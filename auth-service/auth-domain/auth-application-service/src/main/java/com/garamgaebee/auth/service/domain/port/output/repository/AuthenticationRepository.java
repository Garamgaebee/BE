package com.garamgaebee.auth.service.domain.port.output.repository;

import com.garamgaebee.auth.service.domain.entity.Authentication;

import java.util.Optional;

public interface AuthenticationRepository {

    public Optional<Authentication> findAuthenticationByOauthId(String oauthId);

    public Authentication persistAuthentication(Authentication authentication);
}
