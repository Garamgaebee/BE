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
    private String department;
    //0: 학생 1: 졸업생
    private Integer type;
}
