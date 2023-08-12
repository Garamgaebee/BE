package com.garamgaebee.auth.service.application.rest;

import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenResponse;
import com.garamgaebee.auth.service.domain.dto.login.CommonAuthenticationPostCommand;
import com.garamgaebee.auth.service.domain.dto.login.LoginCommand;
import com.garamgaebee.auth.service.domain.dto.login.LoginResponse;
import com.garamgaebee.auth.service.domain.dto.mail.CheckAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.mail.SendMailCommand;
import com.garamgaebee.auth.service.domain.dto.mail.UserSendMailCommand;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;
import com.garamgaebee.auth.service.domain.port.input.service.AuthApplicationService;
import com.garamgaebee.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OauthController {

    // 인증 use-case 객체
    private final AuthApplicationService authApplicationService;

    // Oauth2Login 컨트롤러
    @PostMapping("/login/oauth/{provider}")
    public ResponseEntity<OauthLoginResponse> oauth2Login(@PathVariable("provider") String provider,
                                                          @RequestParam("code") String code) {
        //TODO 등록 안된 멤버일 경우 oauthId와 함께 프론트로 등록 안됐다는 시그널 전송
        return ResponseEntity.ok().body(authApplicationService.oauth2Login(provider, code));
    }

    // 자체로그인 컨트롤러
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) {
        return ResponseEntity.ok().body(authApplicationService.login(loginCommand));
    }


    // refresh token으로 토큰 재발급 컨트롤러
    @PostMapping("/refresh")
    public ResponseEntity<ReissueTokenResponse> reissueJwtTokenByRefreshToken(@RequestBody ReissueTokenCommand reissueTokenCommand) {
        return ResponseEntity.ok().body(authApplicationService.issueTokenByRefreshToken(reissueTokenCommand));
    }

    //TODO 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }

    // 메일 전송 컨트롤러
    @PostMapping("/mail")
    public ResponseEntity<String> sendAuthorizationCodeMail(@RequestBody UserSendMailCommand userSendMailCommand) {
        authApplicationService.sendAuthorizationMailCode(userSendMailCommand);
        return ResponseEntity.ok().body("메일 전송에 성공하였습니다.");
    }

    // 메일 인증코드 검사 컨트롤러
    @PostMapping("/mail/check")
    public ResponseEntity<Boolean> validateMailCode(@RequestBody CheckAuthorizationCodeCommand checkAuthorizationCodeCommand) {
        return ResponseEntity.ok().body(authApplicationService.checkAuthorizationMailCode(checkAuthorizationCodeCommand));
    }

    // 회원가입 컨트롤러
    @PostMapping("/member")
    public ResponseEntity<LoginResponse> createNewMember(@RequestBody CommonAuthenticationPostCommand command) {
        return ResponseEntity.ok().body(authApplicationService.createNewCommonAuthentication(command));
    }
}
