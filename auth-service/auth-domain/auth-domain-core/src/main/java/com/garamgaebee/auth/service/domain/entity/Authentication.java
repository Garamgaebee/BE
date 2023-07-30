package com.garamgaebee.auth.service.domain.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authentication {

    private Long id;
    private String oauthId;
    private UUID memberId;
    private List<Role> roles;


    public Authentication init(String oauthId, UUID memberId) {
        List<Role> initialRoles = new ArrayList<>();
        initialRoles.add(Role.ROLE_GUEST);
        return Authentication.builder()
                .oauthId(oauthId)
                .memberId(memberId)
                .roles(initialRoles)
                .build();
    }

}
