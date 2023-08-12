package com.garamgaebee.auth.service.domain.dto.login;

import com.garamgaebee.auth.service.domain.dto.jwt.JwtTokenInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse {
    private UUID memberId;
    private JwtTokenInfo tokenInfo;
}
