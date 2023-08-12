package com.garamgaebee.auth.service.domain.port.input.service;

import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenResponse;
import com.garamgaebee.auth.service.domain.dto.login.CommonAuthenticationPostCommand;
import com.garamgaebee.auth.service.domain.dto.login.LoginCommand;
import com.garamgaebee.auth.service.domain.dto.login.LoginResponse;
import com.garamgaebee.auth.service.domain.dto.mail.CheckAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.mail.UserSendMailCommand;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;

public interface AuthApplicationService {

    // oauth2 로그인
    public OauthLoginResponse oauth2Login(String oauth2Provider, String code);

    // 자체 로그인
    public LoginResponse login(LoginCommand loginCommand);

    // refresh token으로 토큰 재발급
    public ReissueTokenResponse issueTokenByRefreshToken(ReissueTokenCommand reissueTokenCommand);

    // 인증 메일 전송
    public void sendAuthorizationMailCode(UserSendMailCommand userSendMailCommand);

    // 메일 인증코드 체크
    public Boolean checkAuthorizationMailCode(CheckAuthorizationCodeCommand checkAuthorizationCodeCommand);

    // 회원가입
    public LoginResponse createNewCommonAuthentication(CommonAuthenticationPostCommand commonAuthenticationPostCommand);
}
