package com.garamgaebee.auth.service.domain.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Oauth2Authentication extends Authentication {

    private String oauthId;

    public void init(String email, String oauthId) {
        setEmail(email);
        setMemberId(UUID.randomUUID());
        setRoles(List.of(AuthenticationRole.builder()
                .role(Role.ROLE_USER)
                .build()));
        setOauthId(oauthId);
    }
}
