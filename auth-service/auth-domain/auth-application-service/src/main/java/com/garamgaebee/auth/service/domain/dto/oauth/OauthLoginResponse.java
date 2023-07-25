package com.garamgaebee.auth.service.domain.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OauthLoginResponse {
    private Long memberId;
    private JwtTokenInfo tokenInfo;
}
