package com.garamgaebee.auth.service.domain.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {

    private Long id;
    private String oauthId;
    private Long memberId;
    private List<Role> roles;

    @Builder
    private Authentication(String oauthId, Long memberId, List<Role> roles) {
        this.oauthId = oauthId;
        this.memberId = memberId;
        this.roles = roles;
    }

    public Authentication createAuthentication(String oauthId, Long memberId) {
        List<Role> initialRoles = new ArrayList<>();
        initialRoles.add(Role.ROLE_GUEST);
        return Authentication.builder()
                .oauthId(oauthId)
                .memberId(memberId)
                .roles(initialRoles)
                .build();
    }

}
