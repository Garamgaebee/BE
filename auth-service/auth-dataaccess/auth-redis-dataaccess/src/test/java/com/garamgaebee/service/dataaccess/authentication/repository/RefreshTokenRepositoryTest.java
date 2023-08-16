package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.service.dataaccess.authentication.entity.RefreshToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class RefreshTokenRepositoryTest {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Test
    @DisplayName("refreshToken 저장 테스트")
    void saveWithExpiredTime() {
        // given
        UUID memberId = UUID.randomUUID();
        String token = "test-token";
        long expiredTime = 1209600000L;
        RefreshToken refreshToken = RefreshToken.builder()
                .memberId(memberId)
                .refreshToken(token)
                .build();

        // when
        refreshTokenRepository.saveWithExpiredTime(refreshToken, expiredTime);

        // then
        Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findByMemberId(memberId);

        Assertions.assertThat(findRefreshToken.get().getRefreshToken().equals(token));
    }

    @Test
    @DisplayName("refreshToken read 테스트")
    void findByMemberId() {
    }
}