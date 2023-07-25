package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.oauth.JwtTokenInfo;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthToken;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.input.service.AuthApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthApplicationServiceImpl implements AuthApplicationService {

    private final Oauth2AuthenticationHandler authenticationHandler;
    private final Oauth2UserRegisterHandler userRegisterHandler;
    private final JwtProvideHandler jwtProvideHandler;

    @Override
    public OauthLoginResponse oauth2Login(String oauth2Provider, String code) {

        // oauth2Login
        OauthUserProfile userProfile = authenticationHandler.authenticate(oauth2Provider, code);

        // 유저 DB 저장 or 업데이트
        Authentication authentication = userRegisterHandler.persistOauth2User(userProfile);

        // jwt 토큰 생성
        JwtTokenInfo jwtTokenInfo = jwtProvideHandler.issueJwtToken(authentication);

        // OauthLoginResponse build해서 반환
        return OauthLoginResponse.builder()
                .memberId(authentication.getMemberId())
                .tokenInfo(jwtTokenInfo)
                .build();
    }
}
