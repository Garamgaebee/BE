package com.garamgaebee.service.dataaccess.authentication.adapter;

import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;
import com.garamgaebee.auth.service.domain.port.output.redis.RedisRepository;
import com.garamgaebee.service.dataaccess.authentication.mapper.RedisDataMapper;
import com.garamgaebee.service.dataaccess.authentication.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisDataMapper mapper;

    @Override
    public void persistUserRefreshTokenWithExpiredTime(RegisterRefreshTokenRequest registerRefreshTokenRequest, long expiredTime) {
        refreshTokenRepository.saveWithExpiredTime(
                mapper.registerRefreshTokenRequestToRefreshToken(
                        registerRefreshTokenRequest
                ),
                expiredTime
        );
    }
}
