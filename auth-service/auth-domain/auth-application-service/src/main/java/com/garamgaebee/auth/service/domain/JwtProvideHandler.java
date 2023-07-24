package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.config.JwtTokenProvider;
import com.garamgaebee.auth.service.domain.dto.jwt.CreateJwtRequest;
import com.garamgaebee.auth.service.domain.dto.oauth.JwtTokenInfo;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtProvideHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenInfo issueJwtToken(Authentication authentication) {
        // jwt 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(CreateJwtRequest.builder()
                        .memberId(authentication.getMemberId())
                        .roles(authentication.getRoles())
                .build());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        //TODO 레디스에 refresh token 저장

        return JwtTokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
