package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenResponse;
import com.garamgaebee.auth.service.domain.dto.mail.UserSendMailCommand;
import com.garamgaebee.auth.service.domain.dto.oauth.JwtTokenInfo;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.input.service.AuthApplicationService;
import com.garamgaebee.auth.service.domain.port.output.mail.MailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthApplicationServiceImpl implements AuthApplicationService {

    private final Oauth2AuthenticationHandler authenticationHandler;
    private final Oauth2UserRegisterHandler userRegisterHandler;
    private final JwtProvideHandler jwtProvideHandler;
    private final MailClient mailClient;

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

    @Override
    public ReissueTokenResponse issueTokenByRefreshToken(ReissueTokenCommand reissueTokenCommand) {

        String refreshToken = reissueTokenCommand.getRefreshToken();

        // refreshToken이 저장되어있는지 확인, 저장 안돼있으면 예외
        FindRefreshTokenResponse refreshTokenResponse = jwtProvideHandler.findSavedRefreshTokenValue(refreshToken);

        // 받은 memberId로 멤버 정보 가져오기, 없는 멤버이면 예외
        Authentication authentication = userRegisterHandler.findMemberByMemberId(refreshTokenResponse.getMemberId());

        // refreshToken 삭제
        jwtProvideHandler.deleteRefreshToken(refreshToken);

        // accessToken + refreshToken 새로 발급
        JwtTokenInfo jwtTokenInfo = jwtProvideHandler.issueJwtToken(authentication);

        return ReissueTokenResponse.builder()
                .jwtTokenInfo(jwtTokenInfo)
                .build();
    }

    @Override
    public void sendAuthorizationMailCode(UserSendMailCommand userSendMailCommand) {
        //TODO 이미 있는 메일인지 검사

        //TODO 인증코드 생성

        //TODO 인증코드+이메일 레디스 저장

        //TODO 메일 전송

    }
}
