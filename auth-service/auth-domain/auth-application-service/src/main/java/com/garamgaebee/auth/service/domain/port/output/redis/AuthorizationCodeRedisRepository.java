package com.garamgaebee.auth.service.domain.port.output.redis;

import com.garamgaebee.auth.service.domain.dto.mail.FindAuthorizationCodeResponse;
import com.garamgaebee.auth.service.domain.dto.mail.RegisterAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;

import java.util.Optional;

public interface AuthorizationCodeRedisRepository {
    public void persistAuthorizationCodeWithExpiredTime(RegisterAuthorizationCodeCommand command, long expiredTime);
    public Optional<FindAuthorizationCodeResponse> findAuthorizationCodeByEmail(String email);
}
