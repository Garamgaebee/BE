package com.garamgaebee.auth.service.domain.port.output.redis;

import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;

import java.util.Optional;
import java.util.UUID;

public interface RedisRepository {

    public void persistUserRefreshTokenWithExpiredTime(RegisterRefreshTokenRequest registerRefreshTokenRequest, long expiredTime);
    // public Optional<FindRefreshTokenResponse> findUserRefreshToken(UUID memberId);
}
