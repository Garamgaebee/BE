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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

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
    @Operation(summary = "로그인 API", description = "아이디/비밀번호로 로그인 요청", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "로그인 성공",
                                    summary = "로그인에 성공합니다.",
                                    value =
                                            "{\"memberId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\","
                                            + "\"tokenInfo\": {"
                                            + "\"accessToken\": \"eaoijtho332jfoij_r32jfaejfoie_3afifjajaifjeiellghnaognkvAZaoiEAF\","
                                            + "\"refreshToken\": \"eaoijtho332jfoij_r32jfaejfoie_3afifjajaifjeiellghnaognkvAZaoiEAF\""
                                            + "}"
                                            + "}")})),
            @ApiResponse(responseCode = "400", description = "잘못된 요청(일치하지 않는 아이디/비밀번호 등)", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "로그인 실패",
                                    summary = "로그인에 실패합니다.",
                                    value =
                                            "{\"code\": \"400 BAD_REQUEST\",\n"
                                                    + "\"message\": \"아이디 또는 비밀번호가 일치하지 않습니다.\""
                                                    + "}")})),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "서버 에러",
                                    summary = "서버에서 예기치 못한 에러가 발생합니다.",
                                    value =
                                            "{\"code\": \"500 INTERNAL_SERVER_ERROR\",\n"
                                                    + "\"message\": \"Unexpected error!\""
                                                    + "}")}))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) {
        return ResponseEntity.ok().body(authApplicationService.login(loginCommand));
    }

    // refresh token으로 토큰 재발급 컨트롤러
    @Operation(summary = "토큰재발급 API", description = "refresh token으로 access token을 재발급 받습니다.", responses = {
            @ApiResponse(responseCode = "200", description = "token 재발급 성공", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "token 재발급 성공",
                                    summary = "token 재발급에 성공합니다.",
                                    value =
                                            "{\"tokenInfo\": {"
                                                    + "\"accessToken\": \"eaoijtho332jfoij_r32jfaejfoie_3afifjajaifjeiellghnaognkvAZaoiEAF\","
                                                    + "\"refreshToken\": \"eaoijtho332jfoij_r32jfaejfoie_3afifjajaifjeiellghnaognkvAZaoiEAF\""
                                                    + "}"
                            +"}")})),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "token 재발급 실패",
                                    summary = "token 재발급에 실패합니다.",
                                    value =
                                            "{\"code\": \"400 BAD_REQUEST\",\n"
                                                    + "\"message\": \"유효하지 않은 Refresh Token입니다.\""
                                                    + "}")})),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "서버 에러",
                                    summary = "서버에서 예기치 못한 에러가 발생합니다.",
                                    value =
                                            "{\"code\": \"500 INTERNAL_SERVER_ERROR\",\n"
                                                    + "\"message\": \"Unexpected error!\""
                                                    + "}")}))
    })
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
