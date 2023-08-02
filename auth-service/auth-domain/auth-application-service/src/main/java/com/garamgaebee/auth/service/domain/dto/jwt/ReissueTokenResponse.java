package com.garamgaebee.auth.service.domain.dto.jwt;

import com.garamgaebee.auth.service.domain.dto.oauth.JwtTokenInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReissueTokenResponse {
    private JwtTokenInfo jwtTokenInfo;
}
