package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonAuthenticationHandler {

    private final AuthenticationRepository authenticationRepository;

    public Optional<CommonAuthentication> findCommonAuthenticationByEmail(String email) {
        return authenticationRepository.findCommonAuthenticationByEmail(email);
    }
}
