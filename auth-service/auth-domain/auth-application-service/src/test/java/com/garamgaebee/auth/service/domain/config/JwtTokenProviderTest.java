package com.garamgaebee.auth.service.domain.config;

import com.garamgaebee.auth.service.domain.UserRegisterHandler;
import com.garamgaebee.auth.service.domain.dto.jwt.CreateJwtRequest;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.output.redis.RefreshTokenRedisRepository;
import com.garamgaebee.auth.service.domain.port.output.web.Oauth2RequestClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
public class JwtTokenProviderTest {

    @MockBean
    private Oauth2RequestClient oauth2RequestClient;
    @MockBean
    private UserRegisterHandler oauth2UserRegisterHandler;
    @MockBean
    private RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

}