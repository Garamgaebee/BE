package com.garamgaebee.auth.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthProperties.class)
@RequiredArgsConstructor
public class OauthConfig {

    private final OauthProperties properties;

    /**
     * OauthProvider의 인메모리 저장소를 빈으로 등록
     */
    @Bean
    public InMemoryProviderRepository inMemoryProviderRepository() {
        Map<String, OauthProvider> providers = OauthAdapter.getOauthProviders(properties);
        return new InMemoryProviderRepository(providers);
    }
}
