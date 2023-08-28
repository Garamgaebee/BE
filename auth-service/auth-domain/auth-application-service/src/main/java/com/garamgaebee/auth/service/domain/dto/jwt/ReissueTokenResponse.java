package com.garamgaebee.auth.service.domain.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReissueTokenResponse {
    private JwtTokenInfo tokenInfo;
}
