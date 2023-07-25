package com.garamgaebee.auth.service.domain.config;

import com.garamgaebee.auth.service.domain.Oauth2UserRegisterHandler;
import com.garamgaebee.auth.service.domain.port.output.web.Oauth2RequestClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class InMemoryProviderRepositoryTest {

    @MockBean
    private Oauth2RequestClient oauth2RequestClient;

    @MockBean
    private Oauth2UserRegisterHandler oauth2UserRegisterHandler;

    @Autowired
    private InMemoryProviderRepository repository;

    @Test
    @DisplayName("oauth2 provider 등록 테스트")
    void inMemoryProviderRepositoryTest() {

        OauthProvider provider = repository.findByProviderName("kakao");

        Assertions.assertThat(provider.getClientId().equals("test-id"));
        Assertions.assertThat(provider.getClientSecret().equals("test-secret"));
        Assertions.assertThat(provider.getRedirectUrl().equals("test-redirect-uri"));
        Assertions.assertThat(provider.getTokenUrl().equals("test-token-uri"));
        Assertions.assertThat(provider.getUserInfoUrl().equals("test-user-info-uri"));
    }

}
