package com.garamgaebee.auth.service.domain.port.output.redis;

import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;

public interface RedisRepository {

    public void persistUserRefreshToken(RegisterRefreshTokenRequest registerRefreshTokenRequest);
}
