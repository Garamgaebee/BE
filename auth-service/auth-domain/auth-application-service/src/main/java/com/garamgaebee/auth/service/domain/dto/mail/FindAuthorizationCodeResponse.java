package com.garamgaebee.auth.service.domain.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FindAuthorizationCodeResponse {
    String email;
    String code;
}
