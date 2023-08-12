package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler {
    private final AuthenticationRepository authenticationRepository;

    public Boolean checkEmailExist(String email) {
        return authenticationRepository.checkEmailExist(email);
    }

    public Optional<Authentication> findAuthenticationByMemberId(UUID memberId) {
        return authenticationRepository.findAuthenticationByMemberId(memberId);
    }
}
