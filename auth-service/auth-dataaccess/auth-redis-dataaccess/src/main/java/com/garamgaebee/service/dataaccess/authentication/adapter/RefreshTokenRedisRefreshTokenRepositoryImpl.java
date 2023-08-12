package com.garamgaebee.service.dataaccess.authentication.adapter;

import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.dto.redis.RegisterRefreshTokenRequest;
import com.garamgaebee.auth.service.domain.port.output.redis.RefreshTokenRedisRepository;
import com.garamgaebee.service.dataaccess.authentication.entity.RefreshToken;
import com.garamgaebee.service.dataaccess.authentication.mapper.RedisDataMapper;
import com.garamgaebee.service.dataaccess.authentication.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenRedisRefreshTokenRepositoryImpl implements RefreshTokenRedisRepository {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisDataMapper mapper;

    @Override
    public void persistUserRefreshTokenWithExpiredTime(RegisterRefreshTokenRequest registerRefreshTokenRequest, long expiredTime) {
        refreshTokenRepository.saveWithExpiredTime(
                mapper.registerRefreshTokenRequestToRefreshToken(
                        registerRefreshTokenRequest
                ),
                expiredTime
        );
    }

    @Override
    public Optional<FindRefreshTokenResponse> findUserRefreshToken(String refreshToken) {

        Optional<RefreshToken> refreshTokenWrapper = refreshTokenRepository.findByRefreshToken(refreshToken);

        if(refreshTokenWrapper.isEmpty())
            return Optional.empty();

        return Optional.of(FindRefreshTokenResponse.builder()
                .refreshToken(refreshTokenWrapper.get().getRefreshToken())
                .memberId(refreshTokenWrapper.get().getMemberId())
                .build());
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }


}
