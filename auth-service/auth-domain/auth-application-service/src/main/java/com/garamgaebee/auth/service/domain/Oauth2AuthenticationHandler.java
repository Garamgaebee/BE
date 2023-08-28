package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.config.InMemoryProviderRepository;
import com.garamgaebee.auth.service.domain.config.OauthAttributes;
import com.garamgaebee.auth.service.domain.config.OauthProvider;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthToken;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.entity.Oauth2Authentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.auth.service.domain.port.output.web.Oauth2RequestClient;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Oauth2AuthenticationHandler {

    private final InMemoryProviderRepository inMemoryProviderRepository;
    private final AuthenticationRepository authenticationRepository;
    private final Oauth2RequestClient oauth2RequestClient;

    protected OauthUserProfile authenticate(String oauth2Provider, String code) {
        // 파라미터로 넘어온 provider 이름을 통해 저장된 Oauth2Provider 정보 가져오기
        OauthProvider provider = inMemoryProviderRepository.findByProviderName(oauth2Provider);

        // access token 가져오기
        OauthToken oauthToken = oauth2RequestClient.getToken(code, provider);

        // 유저 정보 가져와 반환
        return getUserProfile(oauth2Provider, oauthToken, provider);
    }

    private OauthUserProfile getUserProfile(String oauth2Provider, OauthToken oauthToken, OauthProvider provider) {
        // token으로 유저 정보 요청
        Map<String, Object> userAttributes = oauth2RequestClient.getUserAttributes(provider, oauthToken);
        // 유저 정보로 OauthUserProfile 생성
        return OauthAttributes.extract(oauth2Provider, userAttributes);
    }

    protected Optional<Oauth2Authentication> findByOauthId(String oauthId) {

        // oauthId로 사용자 조회
        return authenticationRepository.findOauth2AuthenticationByOauthId(oauthId);
    }
}
