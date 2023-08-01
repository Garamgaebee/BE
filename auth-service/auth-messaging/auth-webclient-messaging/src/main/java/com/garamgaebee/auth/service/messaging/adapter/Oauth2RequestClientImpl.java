package com.garamgaebee.auth.service.messaging.adapter;

import com.garamgaebee.auth.service.domain.config.OauthProvider;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthToken;
import com.garamgaebee.auth.service.domain.port.output.web.Oauth2RequestClient;
import com.garamgaebee.auth.service.messaging.publisher.Oauth2RequestWebClientPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Oauth2RequestClientImpl implements Oauth2RequestClient {

    private final Oauth2RequestWebClientPublisher publisher;
    @Override
    public OauthToken getToken(String code, OauthProvider provider) {
        return publisher.requestAccessTokenToAuthorizationServer(code, provider);
    }

    @Override
    public Map<String, Object> getUserAttributes(OauthProvider provider, OauthToken token) {
        return publisher.requestUserProfileToResourceServer(provider, token);
    }
}
