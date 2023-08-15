package com.garamgaebee.auth.service.application.rest;

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
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;
import com.garamgaebee.auth.service.domain.port.input.service.AuthApplicationService;
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
        //TODO 등록 안된 멤버일 경우 oauthId와 함께 프론트로 등록 안됐다는 시그널 전송... 어케하징 ㅜ
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

    // 로그아웃 컨트롤러
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutCommand logoutCommand) {
        authApplicationService.logout(logoutCommand);
        return ResponseEntity.ok().body("로그아웃에 성공하였습니다.");
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

    //닉네임 유효성 검사 컨트롤러 테스트
    @PostMapping("/member/nickname/check")
    public ResponseEntity<ValidateNicknameResponse> validateMemberNickname(@RequestBody ValidateNicknameCommand validateNicknameCommand) {
        return ResponseEntity.ok().body(authApplicationService.validateMemberNickname(validateNicknameCommand));
    }

    // 회원탈퇴 컨트롤러
    @DeleteMapping("/member")
    public ResponseEntity<Boolean> deleteMember(@RequestBody DeleteMemberCommand deleteMemberCommand) {
        authApplicationService.deleteMember(deleteMemberCommand);
        return ResponseEntity.ok().body(true);
    }

}
