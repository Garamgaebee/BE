package com.garamgaebee.auth.service.messaging.publisher;

import com.garamgaebee.auth.service.domain.config.OauthProvider;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthToken;
import io.netty.util.internal.StringUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Component
public class Oauth2RequestWebClientPublisher {

    public OauthToken requestAccessTokenToAuthorizationServer(String code, OauthProvider provider) {
        try {
            Map<String, Object> tokenResponse = WebClient.create()
                    .post()
                    .uri(provider.getTokenUrl())
                    .headers(header -> {
                        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                    })
                    .bodyValue(tokenRequest(code, provider))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return OauthToken.builder()
                    .accessToken((String)tokenResponse.get("access_token"))
                    .tokenType((String)tokenResponse.get("token_type"))
                    .scope((String)tokenResponse.get("scope"))
                    .build();
        } catch (Exception e) {
            //TODO 실패한 경우 반환 에러 정의
            return null;
        }
    }

    // OAuth 서버에서 유저 정보 map으로 가져오기
    public Map<String, Object> requestUserProfileToResourceServer(OauthProvider provider, OauthToken token) {
        try {
            return WebClient.create()
                    .get()
                    .uri(provider.getUserInfoUrl())
                    .headers(header -> {
                        header.add("Authorization", "Bearer " + token.getAccessToken());
                        header.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
                    })
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();
        } catch (Exception e) {
            //TODO 실패한 경우 반환 에러 정의
            e.printStackTrace();
            return null;
        }
    }

    // OAuth 서버에서 access token 받아오기
    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUrl());
        formData.add("client_id", provider.getClientId());
        formData.add("client_secret", provider.getClientSecret());
        return formData;
    }

}
