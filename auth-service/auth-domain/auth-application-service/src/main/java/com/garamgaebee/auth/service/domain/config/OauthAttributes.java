package com.garamgaebee.auth.service.domain.config;

import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;

import java.util.Arrays;
import java.util.Map;

/**
 * Resource Server로부터 전달받은 유저 정보 바인딩
 */
public enum OauthAttributes {
    KAKAO("kakao") {
        @Override
        public OauthUserProfile of(Map<String, Object> attributes) {
            //TODO attribute 이름 카카오 api에 맞게 수정
            return OauthUserProfile.builder()
                    .oauthId(String.valueOf(attributes.get("id")))
                    .email((String) attributes.get("email"))
                    .name((String) attributes.get("name"))
                    .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static OauthUserProfile extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract OauthUserProfile of(Map<String, Object> attributes);
}
