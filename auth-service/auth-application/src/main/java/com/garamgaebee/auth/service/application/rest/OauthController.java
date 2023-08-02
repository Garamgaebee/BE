package com.garamgaebee.auth.service.application.rest;

import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenResponse;
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
    @PostMapping("/login/{provider}")
    public ResponseEntity<OauthLoginResponse> oauth2Login(@PathVariable("provider") String provider,
                                                          @RequestParam("code") String code) {

        return ResponseEntity.ok().body(authApplicationService.oauth2Login(provider, code));
    }

    // refresh token으로 토큰 재발급 컨트롤러
    @PostMapping("/refresh")
    public ResponseEntity<ReissueTokenResponse> reissueJwtTokenByRefreshToken(@RequestBody ReissueTokenCommand reissueTokenCommand) {
        return ResponseEntity.ok().body(authApplicationService.issueTokenByRefreshToken(reissueTokenCommand));
    }

    //TODO 로그아웃

    //TODO 메일 인증
}
