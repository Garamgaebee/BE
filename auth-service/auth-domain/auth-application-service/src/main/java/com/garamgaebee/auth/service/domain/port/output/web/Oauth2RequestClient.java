package com.garamgaebee.auth.service.domain.port.output.web;

import com.garamgaebee.auth.service.domain.config.OauthProvider;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthToken;

import java.util.Map;

public interface Oauth2RequestClient {

    OauthToken getToken(String code, OauthProvider provider);
    Map<String, Object> getUserAttributes(OauthProvider provider, OauthToken token);
}
