package com.garamgaebee.service.dataaccess.authentication.adapter;

import com.garamgaebee.auth.service.domain.dto.mail.FindAuthorizationCodeResponse;
import com.garamgaebee.auth.service.domain.dto.mail.RegisterAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.redis.FindRefreshTokenResponse;
import com.garamgaebee.auth.service.domain.port.output.redis.AuthorizationCodeRedisRepository;
import com.garamgaebee.service.dataaccess.authentication.repository.AuthorizationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorizationCodeRedisRepositoryImpl implements AuthorizationCodeRedisRepository {

    private final AuthorizationCodeRepository authorizationCodeRepository;

    @Override
    public void persistAuthorizationCodeWithExpiredTime(RegisterAuthorizationCodeCommand command, long expiredTime) {
        authorizationCodeRepository.saveWithExpiredTime(command, expiredTime);
    }

    @Override
    public Optional<FindAuthorizationCodeResponse> findAuthorizationCodeByEmail(String email) {
        Optional<String> authorizationCodeWrapper = authorizationCodeRepository.findByEmail(email);

        if(authorizationCodeWrapper.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(FindAuthorizationCodeResponse.builder()
                        .email(email)
                        .code(authorizationCodeWrapper.get())
                .build());
    }
}
