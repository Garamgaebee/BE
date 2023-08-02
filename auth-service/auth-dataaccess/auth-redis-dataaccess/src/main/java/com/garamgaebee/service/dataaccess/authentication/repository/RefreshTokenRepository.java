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
        ValueOperations<String, UUID> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getMemberId());
        redisTemplate.expire(refreshToken.getRefreshToken(), expiredTime, TimeUnit.MILLISECONDS);
    }

    public Optional<RefreshToken> findByRefreshToken(final String refreshToken) {
        ValueOperations<String, UUID> valueOperations = redisTemplate.opsForValue();
        UUID memberId = valueOperations.get(refreshToken);

        if (Objects.isNull(memberId)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(refreshToken, memberId));
    }

    public void deleteByRefreshToken(final String refreshToken) {
        redisTemplate.delete(refreshToken);
    }
}