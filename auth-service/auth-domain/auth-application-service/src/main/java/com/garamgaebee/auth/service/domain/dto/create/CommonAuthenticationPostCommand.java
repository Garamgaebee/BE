package com.garamgaebee.auth.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommonAuthenticationPostCommand {
    private String email;
    private String password;
    private String nickname;
    private String type;
    private String department;
    private String company;

}
