package com.garamgaebee.auth.service.domain.port.output.redis;

import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;

import java.util.Optional;

public interface RefreshTokenRedisRepository {

    public void persistUserRefreshTokenWithExpiredTime(RegisterRefreshTokenRequest registerRefreshTokenRequest, long expiredTime);
    public Optional<FindRefreshTokenResponse> findUserRefreshToken(String refreshToken);
    public void deleteRefreshToken(String refreshToken);
}
