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
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.common.exception.ErrorDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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

    /**
     * 로그인 API
     */
    @Operation(summary = "로그인 API", description = "아이디/비밀번호로 로그인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "아이디 또는 비밀번호가 일치하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) {
        return ResponseEntity.ok().body(authApplicationService.login(loginCommand));
    }

    /**
     * 토큰 재발급 API
     */
    @Operation(summary = "토큰 재발급 API", description = "Refresh Token을 사용하여 토큰을 재발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReissueTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 Refresh Token입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "400", description = "등록되지 않은 유저입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/refresh")
    public ResponseEntity<ReissueTokenResponse> reissueJwtTokenByRefreshToken(@RequestBody ReissueTokenCommand reissueTokenCommand) {
        return ResponseEntity.ok().body(authApplicationService.issueTokenByRefreshToken(reissueTokenCommand));
    }

    /**
     * 로그아웃 API
     */
    @Operation(summary = "로그아웃 API", description = "Refresh Token과 함께 로그아웃을 요청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "로그아웃에 성공하였습니다."
            ),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutCommand logoutCommand) {
        authApplicationService.logout(logoutCommand);
        return ResponseEntity.ok().body("로그아웃에 성공하였습니다.");
    }

    /**
     * 인증메일 전송 API
     */
    @Operation(summary = "인증메일 전송 API", description = "이메일 주소를 받아 해당 이메일로 인증코드가 담긴 메일을 발송합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Body에 String으로 메일 전송에 성공하였습니다."
            ),
            @ApiResponse(responseCode = "400", description = "이미 가입된 이메일입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/mail")
    public ResponseEntity<String> sendAuthorizationCodeMail(@RequestBody UserSendMailCommand userSendMailCommand) {
        authApplicationService.sendAuthorizationMailCode(userSendMailCommand);
        return ResponseEntity.ok().body("메일 전송에 성공하였습니다.");
    }

    /**
     * 인증메일 전송 API
     */
    @Operation(summary = "인증코드 검사 API", description = "이메일과 인증코드를 받아 해당 이메일로 발송된 인증코드가 맞는지 검사합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "true or false"
            ),
            @ApiResponse(responseCode = "400", description = "인증코드가 존재하지 않는 이메일입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/mail/check")
    public ResponseEntity<Boolean> validateMailCode(@RequestBody CheckAuthorizationCodeCommand checkAuthorizationCodeCommand) {
        return ResponseEntity.ok().body(authApplicationService.checkAuthorizationMailCode(checkAuthorizationCodeCommand));
    }

    /**
     * 회원가입 API
     */
    @Operation(summary = "회원가입 API", description = "회원 정보를 받아 회원을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "이미 가입된 이메일입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "400", description = "이미 사용 중인 닉네임입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/member")
    public ResponseEntity<LoginResponse> createNewMember(@RequestBody CommonAuthenticationPostCommand command) {
        return ResponseEntity.ok().body(authApplicationService.createNewCommonAuthentication(command));
    }

    /**
     * 닉네임 중복체크 API
     */
    @Operation(summary = "닉네임 중복체크 API", description = "닉네임 중복 여부를 검사합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ValidateNicknameResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/member/nickname/check")
    public ResponseEntity<ValidateNicknameResponse> validateMemberNickname(@RequestBody ValidateNicknameCommand validateNicknameCommand) {
        return ResponseEntity.ok().body(authApplicationService.validateMemberNickname(validateNicknameCommand));
    }

    /**
     * 회원탈퇴 API
     */
    @Operation(summary = "회원탈퇴 API", description = "회원을 탈퇴 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "true"
            ),
            @ApiResponse(responseCode = "400", description = "등록되지 않은 유저입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error!",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @DeleteMapping("/member")
    public ResponseEntity<Boolean> deleteMember(@RequestBody DeleteMemberCommand deleteMemberCommand) {
        authApplicationService.deleteMember(deleteMemberCommand);
        return ResponseEntity.ok().body(true);
    }

}
