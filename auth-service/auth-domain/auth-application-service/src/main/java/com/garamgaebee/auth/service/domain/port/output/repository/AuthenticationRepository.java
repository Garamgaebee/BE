package com.garamgaebee.auth.service.domain.port.output.repository;

import com.garamgaebee.auth.service.domain.entity.Authentication;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationRepository {

    public Optional<Authentication> findAuthenticationByOauthId(String oauthId);

    public Optional<Authentication> findAuthenticationByMemberId(UUID memberId);

    public Authentication persistAuthentication(Authentication authentication);
}
