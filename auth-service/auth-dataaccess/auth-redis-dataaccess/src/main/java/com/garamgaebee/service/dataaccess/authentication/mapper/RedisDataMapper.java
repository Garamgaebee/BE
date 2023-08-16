package com.garamgaebee.service.dataaccess.authentication.mapper;

import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;
import com.garamgaebee.service.dataaccess.authentication.entity.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class RedisDataMapper {

    public RefreshToken registerRefreshTokenRequestToRefreshToken(RegisterRefreshTokenRequest registerRefreshTokenRequest) {
        return RefreshToken.builder()
                .refreshToken(registerRefreshTokenRequest.getRefreshToken())
                .memberId(registerRefreshTokenRequest.getMemberId())
                .build();
    }
}
