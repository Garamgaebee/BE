package com.garamgaebee.auth.service.domain.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InMemoryProviderRepositoryTest {

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
