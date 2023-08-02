package com.garamgaebee.auth.service.domain.port.input.service;

import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenCommand;
import com.garamgaebee.auth.service.domain.dto.jwt.ReissueTokenResponse;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthLoginResponse;

public interface AuthApplicationService {

    public OauthLoginResponse oauth2Login(String oauth2Provider, String code);

    public ReissueTokenResponse issueTokenByRefreshToken(ReissueTokenCommand reissueTokenCommand);
}
