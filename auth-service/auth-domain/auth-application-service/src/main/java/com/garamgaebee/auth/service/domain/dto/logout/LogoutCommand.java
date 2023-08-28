package com.garamgaebee.auth.service.domain.dto.logout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogoutCommand {
    private String refreshToken;
}
