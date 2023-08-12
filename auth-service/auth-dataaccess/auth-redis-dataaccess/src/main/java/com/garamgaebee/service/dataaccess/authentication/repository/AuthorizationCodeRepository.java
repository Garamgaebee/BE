package com.garamgaebee.service.dataaccess.authentication.repository;

import com.garamgaebee.auth.service.domain.dto.mail.RegisterAuthorizationCodeCommand;
import com.garamgaebee.service.dataaccess.authentication.entity.RefreshToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class AuthorizationCodeRepository {

    private RedisTemplate redisTemplate;

    public AuthorizationCodeRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveWithExpiredTime(final RegisterAuthorizationCodeCommand command, final long expiredTime) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(command.getEmail(), command.getCode());
        redisTemplate.expire(command.getEmail(), expiredTime, TimeUnit.MILLISECONDS);
    }

    public Optional<String> findByEmail(final String email) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String authorizationCode = valueOperations.get(email);

        if (Objects.isNull(authorizationCode)) {
            return Optional.empty();
        }

        return Optional.of(authorizationCode);
    }
}
