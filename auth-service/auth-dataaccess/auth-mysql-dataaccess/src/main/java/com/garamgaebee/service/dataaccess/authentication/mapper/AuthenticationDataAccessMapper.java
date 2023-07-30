package com.garamgaebee.service.dataaccess.authentication.mapper;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.vo.Role;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationRoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationDataAccessMapper {

    public Authentication authenticationEntityToAuthentication(AuthenticationEntity authenticationEntity) {
        return Authentication.builder()
                .id(authenticationEntity.getId())
                .memberId(authenticationEntity.getMemberId())
                .oauthId(authenticationEntity.getOauthId())
                .roles(roleEntityListToAuthenticationRoleList(authenticationEntity.getRoles()))
                .build();
    }

    public List<Role> roleEntityListToAuthenticationRoleList(List<AuthenticationRoleEntity> authenticationRoleEntityList) {
        return authenticationRoleEntityList.stream().map(authenticationRoleEntity ->
            authenticationRoleEntity.getRole()
        ).collect(Collectors.toList());
    }

    public AuthenticationEntity authenticationToAuthenticationEntity(Authentication authentication) {
        AuthenticationEntity authenticationEntity = AuthenticationEntity.builder()
                .id(authentication.getId())
                .memberId(authentication.getMemberId())
                .oauthId(authentication.getOauthId())
                .build();
        authentication.getRoles().stream().forEach(role -> {
            authenticationEntity.addRole(roleToAuthenticationRoleEntity(role));
        });

        return authenticationEntity;
    }

    public AuthenticationRoleEntity roleToAuthenticationRoleEntity(Role role) {
        return AuthenticationRoleEntity.builder()
                .role(role)
                .build();
    }
}
