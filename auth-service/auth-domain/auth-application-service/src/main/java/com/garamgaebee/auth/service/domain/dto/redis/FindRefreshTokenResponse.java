package com.garamgaebee.auth.service.domain.dto.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class FindRefreshTokenResponse {
    private UUID memberId;
    private String refreshToken;
}
