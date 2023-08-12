package com.garamgaebee.auth.service.domain.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CheckAuthorizationCodeCommand {
    private String email;
    private String code;
}
