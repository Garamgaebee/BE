package com.garamgaebee.auth.service.domain;

import java.util.HashMap;
import java.util.Map;
/**
 * OauthProvider를 저장하는 저장소
 */
public class InMemoryProviderRepository {
    private final Map<String, OauthProvider> providers;

    public InMemoryProviderRepository(Map<String, OauthProvider> providers) {
        this.providers = new HashMap<>(providers);
    }

    public OauthProvider findByProviderName(String name) {
        return providers.get(name);
    }
}
