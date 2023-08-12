package com.garamgaebee.auth.service.domain.port.output.repository;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.entity.Oauth2Authentication;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationRepository {

    public Optional<Oauth2Authentication> findOauth2AuthenticationByOauthId(String oauthId);

    public Optional<CommonAuthentication> findCommonAuthenticationByEmail(String email);

    public Optional<Authentication> findAuthenticationByMemberId(UUID memberId);

    public Oauth2Authentication persistOauth2Authentication(Oauth2Authentication oauth2Authentication);

    public CommonAuthentication persistCommonAuthentication(CommonAuthentication commonAuthentication);

    public Boolean checkEmailExist(String email);
}
