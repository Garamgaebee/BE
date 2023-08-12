package com.garamgaebee.auth.service.domain.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CommonAuthentication extends Authentication {

    private String password;

    public void init(String email, String password) {
        setEmail(email);
        setMemberId(UUID.randomUUID());
        setRoles(List.of(AuthenticationRole.builder()
                .role(Role.ROLE_USER)
                .build()));
        setPassword(password);
    }
}
