package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.service.dataaccess.authentication.entity.RefreshToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepository {

    private RedisTemplate redisTemplate;

    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveWithExpiredTime(final RefreshToken refreshToken, final long expiredTime) {
        ValueOperations<UUID, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getMemberId(), refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getMemberId(), expiredTime, TimeUnit.MILLISECONDS);
    }

    public Optional<RefreshToken> findByMemberId(final UUID memberId) {
        ValueOperations<UUID, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(memberId);

        if (Objects.isNull(refreshToken)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(memberId, refreshToken));
    }
}