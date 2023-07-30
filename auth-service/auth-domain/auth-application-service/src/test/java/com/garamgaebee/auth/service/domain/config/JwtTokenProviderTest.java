package com.garamgaebee.auth.service.domain.config;

import com.garamgaebee.auth.service.domain.Oauth2UserRegisterHandler;
import com.garamgaebee.auth.service.domain.dto.jwt.CreateJwtRequest;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.output.redis.RedisRepository;
import com.garamgaebee.auth.service.domain.port.output.web.Oauth2RequestClient;
import com.garamgaebee.auth.service.domain.vo.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtTokenProviderTest {

    @MockBean
    private Oauth2RequestClient oauth2RequestClient;
    @MockBean
    private Oauth2UserRegisterHandler oauth2UserRegisterHandler;
    @MockBean
    private RedisRepository redisRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("accessToken 생성 테스트")
    void createAccessToken() {
        // given
        Authentication authentication = new Authentication().init("temp", UUID.randomUUID());
        CreateJwtRequest createJwtRequest = CreateJwtRequest.builder()
                .memberId(authentication.getMemberId())
                .roles(authentication.getRoles())
                .build();

        // when
        String accessToken = jwtTokenProvider.createAccessToken(createJwtRequest);

        // then
        Assertions.assertThat(jwtTokenProvider.getPayload(accessToken).equals(authentication.getMemberId().toString()));
    }
}