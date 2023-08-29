package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.create.ValidateNicknameCommand;
import com.garamgaebee.auth.service.domain.dto.create.ValidateNicknameResponse;
import com.garamgaebee.auth.service.domain.dto.delete.DeleteMemberCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenResponse;
import com.garamgaebee.auth.service.domain.dto.create.CommonAuthenticationPostCommand;
import com.garamgaebee.auth.service.domain.dto.login.LoginCommand;
import com.garamgaebee.auth.service.domain.dto.login.LoginResponse;
import com.garamgaebee.auth.service.domain.dto.logout.LogoutCommand;
import com.garamgaebee.auth.service.domain.dto.mail.CheckAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.mail.UserSendMailCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.JwtTokenInfo;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.entity.Oauth2Authentication;
import com.garamgaebee.auth.service.domain.port.input.service.AuthApplicationService;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthApplicationServiceImpl implements AuthApplicationService {

    private final Oauth2AuthenticationHandler oauth2AuthenticationHandler;
    private final CommonAuthenticationHandler commonAuthenticationHandler;
    private final AuthenticationHandler authenticationHandler;
    private final UserRegisterHandler userRegisterHandler;
    private final JwtProvideHandler jwtProvideHandler;
    private final AuthorizationCodeHandler authorizationCodeHandler;

    // oauth2 로그인
    @Override
    public OauthLoginResponse oauth2Login(String oauth2Provider, String code) {

        // oauth2Login
        OauthUserProfile userProfile = oauth2AuthenticationHandler.authenticate(oauth2Provider, code);

        // 유저 조회
        Oauth2Authentication oauth2Authentication = oauth2AuthenticationHandler.findByOauthId(userProfile.getOauthId())
                .orElseThrow(() -> new BaseException(BaseErrorCode.NOT_REGISTERED_MEMBER));

        // jwt 토큰 생성
        JwtTokenInfo jwtTokenInfo = jwtProvideHandler.issueJwtToken(oauth2Authentication);

        // OauthLoginResponse build해서 반환
        return OauthLoginResponse.builder()
                .memberId(oauth2Authentication.getMemberId())
                .tokenInfo(jwtTokenInfo)
                .build();
    }

    // 자체 로그인
    @Override
    public LoginResponse login(LoginCommand loginCommand) {

        // 이메일로 Authentication 조회
        CommonAuthentication commonAuthentication = commonAuthenticationHandler.findCommonAuthenticationByEmail(loginCommand.getEmail())
                .orElseThrow(() -> new BaseException(BaseErrorCode.WRONG_ID_PASSWORD));

        // 비밀번호 일치 여부 확인
        if(!commonAuthentication.getPassword().equals(loginCommand.getPassword())) {
            throw new BaseException(BaseErrorCode.WRONG_ID_PASSWORD);
        }

        // jwt token 발급
        JwtTokenInfo jwtTokenInfo = jwtProvideHandler.issueJwtToken(commonAuthentication);

        return LoginResponse.builder()
                .tokenInfo(jwtTokenInfo)
                .memberId(commonAuthentication.getMemberId())
                .build();
    }

    // refresh token으로 jwt 재발급
    @Override
    public ReissueTokenResponse issueTokenByRefreshToken(ReissueTokenCommand reissueTokenCommand) {

        String refreshToken = reissueTokenCommand.getRefreshToken();

        // refreshToken이 저장되어있는지 확인, 저장 안돼있으면 예외
        FindRefreshTokenResponse refreshTokenResponse = jwtProvideHandler.findSavedRefreshTokenValue(refreshToken)
                .orElseThrow(() -> new BaseException(BaseErrorCode.INVALID_REFRESH_TOKEN));

        // 받은 memberId로 멤버 정보 가져오기, 없는 멤버이면 예외
        Authentication authentication = authenticationHandler.findAuthenticationByMemberId(refreshTokenResponse.getMemberId())
                .orElseThrow(() -> new BaseException(BaseErrorCode.NOT_REGISTERED_MEMBER));

        // refreshToken 삭제
        jwtProvideHandler.deleteRefreshToken(refreshToken);

        // accessToken + refreshToken 새로 발급
        JwtTokenInfo jwtTokenInfo = jwtProvideHandler.issueJwtToken(authentication);

        return ReissueTokenResponse.builder()
                .tokenInfo(jwtTokenInfo)
                .build();
    }

    // 인증코드 메일 발송
    @Override
    public void sendAuthorizationMailCode(UserSendMailCommand userSendMailCommand) {
        // 이미 가입된 이메일 주소인지 검사
        if(authenticationHandler.checkEmailExist(userSendMailCommand.getAddress())) {
            // 이미 가입된 경우 에러 반환
            throw new BaseException(BaseErrorCode.ALREADY_EXIST_EMAIL);
        }
        // 인증코드 생성
        String code = authorizationCodeHandler.createAuthorizationCode(userSendMailCommand.getAddress());

        // 메일 전송
        authorizationCodeHandler.sendAuthorizationCodeToMail(userSendMailCommand.getAddress(), code);
    }

    // 인증 코드 검사
    @Override
    public Boolean checkAuthorizationMailCode(CheckAuthorizationCodeCommand checkAuthorizationCodeCommand) {

        // email로 인증코드 레디스에서 꺼내서 검사
        if(!authorizationCodeHandler.checkAuthorizationCode(checkAuthorizationCodeCommand)) {
            throw new BaseException(BaseErrorCode.WRONG_AUTHORIZATION_CODE);
        }

        return true;
    }

    // 회원가입
    @Override
    public LoginResponse createNewCommonAuthentication(CommonAuthenticationPostCommand commonAuthenticationPostCommand) {

        // email, 닉네임 중복 검사
        if(authenticationHandler.checkEmailExist(commonAuthenticationPostCommand.getEmail())) {
            throw new BaseException(BaseErrorCode.ALREADY_EXIST_EMAIL);
        }
        if(userRegisterHandler.checkNicknameExist(commonAuthenticationPostCommand.getNickname())) {
            throw new BaseException(BaseErrorCode.ALREADY_EXIST_NICKNAME);
        }

        // 회원 등록
        CommonAuthentication commonAuthentication = userRegisterHandler.registerCommonAuthentication(commonAuthenticationPostCommand);

        // jwt 발급
        JwtTokenInfo jwtTokenInfo = jwtProvideHandler.issueJwtToken(commonAuthentication);

        // 반환값 build 하여 반환
        return LoginResponse.builder()
                .tokenInfo(jwtTokenInfo)
                .memberId(commonAuthentication.getMemberId())
                .build();
    }

    // 로그아웃
    @Override
    public void logout(LogoutCommand logoutCommand) {

        // redis에서 refreshToken 삭제
        jwtProvideHandler.deleteRefreshToken(logoutCommand.getRefreshToken());

    }

    // 닉네임 유효성 검사
    @Override
    public ValidateNicknameResponse validateMemberNickname(ValidateNicknameCommand validateNicknameCommand) {

        ValidateNicknameResponse response = ValidateNicknameResponse.builder()
                .isExist(userRegisterHandler.checkNicknameExist(validateNicknameCommand.getNickname()))
                .build();
        if(response.getIsExist() == null) {
            // 있어선 안되는 에러
            throw new RuntimeException();
        }
        return response;
    }

    @Override
    public void deleteMember(DeleteMemberCommand deleteMemberCommand) {
        Authentication authentication = authenticationHandler.findAuthenticationByMemberId(deleteMemberCommand.getMemberId())
                .orElseThrow(() -> new BaseException(BaseErrorCode.NOT_REGISTERED_MEMBER));
        // DB에서 멤버 삭제
        authenticationHandler.deleteAuthenticationByMemberId(authentication.getMemberId());
        // refresh token 삭제
        jwtProvideHandler.deleteRefreshToken(deleteMemberCommand.getRefreshToken());
    }

}
