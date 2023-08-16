package com.garamgaebee.service.dataaccess.authentication.mapper;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.entity.AuthenticationRole;
import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.entity.Oauth2Authentication;
import com.garamgaebee.auth.service.domain.vo.Role;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationRoleEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.CommonAuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.Oauth2AuthenticationEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationDataAccessMapper {
    public CommonAuthenticationEntity commonAuthenticationToCommonAuthenticationEntity(CommonAuthentication commonAuthentication) {
        CommonAuthenticationEntity commonAuthenticationEntity = CommonAuthenticationEntity.builder()
                .id(commonAuthentication.getId())
                .email(commonAuthentication.getEmail())
                .password(commonAuthentication.getPassword())
                .memberId(commonAuthentication.getMemberId())
                .build();

        commonAuthentication.getRoles().stream().forEach(authenticationRole -> {
            commonAuthenticationEntity.addRole(
                    authenticationRoleToAuthenticationRoleEntity(authenticationRole)
            );
        });

        return commonAuthenticationEntity;
    }

    public Oauth2AuthenticationEntity oauth2AuthenticationToOauth2AuthenticationEntity(Oauth2Authentication oauth2Authentication) {
        Oauth2AuthenticationEntity oauth2AuthenticationEntity = Oauth2AuthenticationEntity.builder()
                .id(oauth2Authentication.getId())
                .email(oauth2Authentication.getEmail())
                .oauthId(oauth2Authentication.getOauthId())
                .memberId(oauth2Authentication.getMemberId())
                .build();

        oauth2Authentication.getRoles().stream().forEach(authenticationRole -> {
            oauth2AuthenticationEntity.addRole(
                    authenticationRoleToAuthenticationRoleEntity(authenticationRole)
            );
        });

        return oauth2AuthenticationEntity;
    }

    public Authentication authenticationEntityToAuthentication(AuthenticationEntity authenticationEntity) {
        return Authentication.builder()
                .id(authenticationEntity.getId())
                .email(authenticationEntity.getEmail())
                .memberId(authenticationEntity.getMemberId())
                .roles(authenticationRoleEntityListToAuthenticationRoleList(authenticationEntity.getRoles()))
                .build();
    }

    public CommonAuthentication commonAuthenticationEntityToCommonAuthentication(CommonAuthenticationEntity commonAuthenticationEntity) {
        System.out.println("commonAuthenticationEntity.getPassword() result : ");
        System.out.println(commonAuthenticationEntity.getPassword());

        return CommonAuthentication.builder()
                .id(commonAuthenticationEntity.getId())
                .email(commonAuthenticationEntity.getEmail())
                .password(commonAuthenticationEntity.getPassword())
                .memberId(commonAuthenticationEntity.getMemberId())
                .roles(authenticationRoleEntityListToAuthenticationRoleList(commonAuthenticationEntity.getRoles()))
                .build();
    }

    public Oauth2Authentication oauth2AuthenticationEntityToOauth2Authentication(Oauth2AuthenticationEntity oauth2AuthenticationEntity) {
        return Oauth2Authentication.builder()
                .id(oauth2AuthenticationEntity.getId())
                .email(oauth2AuthenticationEntity.getEmail())
                .oauthId(oauth2AuthenticationEntity.getOauthId())
                .memberId(oauth2AuthenticationEntity.getMemberId())
                .roles(authenticationRoleEntityListToAuthenticationRoleList(oauth2AuthenticationEntity.getRoles()))
                .build();
    }

    public List<AuthenticationRole> authenticationRoleEntityListToAuthenticationRoleList(List<AuthenticationRoleEntity> authenticationRoleEntityList) {
        return authenticationRoleEntityList.stream().map(authenticationRoleEntity ->
            AuthenticationRole.builder()
                    .id(authenticationRoleEntity.getId())
                    .role(authenticationRoleEntity.getRole())
                    .build()
        ).collect(Collectors.toList());
    }

    public AuthenticationRoleEntity authenticationRoleToAuthenticationRoleEntity(AuthenticationRole authenticationRole) {
        return AuthenticationRoleEntity.builder()
                .role(authenticationRole.getRole())
                .build();
    }
}
