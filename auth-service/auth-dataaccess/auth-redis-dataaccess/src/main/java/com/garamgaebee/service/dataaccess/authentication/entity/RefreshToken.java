package com.garamgaebee.service.dataaccess.authentication.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RefreshToken {
    private UUID memberId;
    private String refreshToken;

    @Builder
    public RefreshToken(final UUID memberId, final String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }
}
