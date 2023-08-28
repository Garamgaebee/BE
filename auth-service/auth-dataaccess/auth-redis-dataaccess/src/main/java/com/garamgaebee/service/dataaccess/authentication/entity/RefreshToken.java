package com.garamgaebee.service.dataaccess.authentication.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RefreshToken {
    private String refreshToken;
    private UUID memberId;

    @Builder
    public RefreshToken(final String refreshToken, final UUID memberId) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}
