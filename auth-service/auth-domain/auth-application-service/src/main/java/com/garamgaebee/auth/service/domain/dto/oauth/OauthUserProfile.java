package com.garamgaebee.auth.service.domain.dto.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthUserProfile {
    private final String oauthId;
    private final String email;
    private final String name;
}
