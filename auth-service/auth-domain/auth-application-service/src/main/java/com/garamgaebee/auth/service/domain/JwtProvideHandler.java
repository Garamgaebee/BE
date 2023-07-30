package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.config.JwtTokenProvider;
import com.garamgaebee.auth.service.domain.dto.jwt.CreateJwtRequest;
import com.garamgaebee.auth.service.domain.dto.oauth.JwtTokenInfo;
import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.output.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtProvideHandler {

    @Value("${jwt.refresh-token.expired-time}")
    private long refreshTokenExpiredTime;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisRepository redisRepository;

    public JwtTokenInfo issueJwtToken(Authentication authentication) {
        // jwt 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(CreateJwtRequest.builder()
                        .memberId(authentication.getMemberId())
                        .roles(authentication.getRoles())
                .build());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        // 레디스에 refresh token 저장
        redisRepository.persistUserRefreshTokenWithExpiredTime(
                RegisterRefreshTokenRequest.builder()
                        .memberId(authentication.getMemberId())
                        .refreshToken(refreshToken)
                        .build(),
                refreshTokenExpiredTime
        );

        return JwtTokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
