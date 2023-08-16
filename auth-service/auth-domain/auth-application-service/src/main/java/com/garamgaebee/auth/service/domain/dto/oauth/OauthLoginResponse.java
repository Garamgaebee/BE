package com.garamgaebee.auth.service.domain.dto.oauth;

import com.garamgaebee.auth.service.domain.dto.jwt.JwtTokenInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class OauthLoginResponse {
    private UUID memberId;
    private JwtTokenInfo tokenInfo;
}
