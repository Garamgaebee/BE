package com.garamgaebee.auth.service.domain.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class JwtTokenInfo {
    private String accessToken;
    private String refreshToken;
}
