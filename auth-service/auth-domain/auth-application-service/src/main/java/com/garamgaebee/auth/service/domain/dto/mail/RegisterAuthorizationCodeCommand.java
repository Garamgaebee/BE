package com.garamgaebee.auth.service.domain.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RegisterAuthorizationCodeCommand {
    private String email;
    private String code;
}
