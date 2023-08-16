package com.garamgaebee.auth.service.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginCommand {
    private String email;
    private String password;
}
