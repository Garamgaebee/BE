package com.garamgaebee.auth.service.application.rest;

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

    // 인증 use-case 객체 선언
    private final AuthApplicationService authApplicationService;

    // Oauth2Login 컨트롤러 작성
    @PostMapping("/login/{provider}")
    public ResponseEntity<OauthLoginResponse> oauth2Login(@PathVariable("provider") String provider,
                                                          @RequestParam("code") String code) {

        return ResponseEntity.ok().body(authApplicationService.oauth2Login(provider, code));
    }
}
