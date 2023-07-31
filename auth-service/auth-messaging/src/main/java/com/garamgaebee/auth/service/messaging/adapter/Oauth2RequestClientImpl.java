package com.garamgaebee.auth.service.messaging.adapter;

import com.garamgaebee.auth.service.domain.config.OauthProvider;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthToken;
import com.garamgaebee.auth.service.domain.port.output.web.Oauth2RequestClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Oauth2RequestClientImpl implements Oauth2RequestClient {
    @Override
    public OauthToken getToken(String code, OauthProvider provider) {
        return null;
    }

    @Override
    public Map<String, Object> getUserAttributes(OauthProvider provider, OauthToken token) {
        return null;
    }
}
