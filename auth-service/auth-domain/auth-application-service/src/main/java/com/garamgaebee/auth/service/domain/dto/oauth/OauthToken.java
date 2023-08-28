package com.garamgaebee.auth.service.domain.dto.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OauthToken {
    private String accessToken;
    private String scope;
    private String tokenType;

    @Builder
    public OauthToken(String accessToken, String scope, String tokenType) {
        this.accessToken = accessToken;
        this.scope = scope;
        this.tokenType = tokenType;
    }
}