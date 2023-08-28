package com.garamgaebee.service.dataaccess.authentication.adapter;

import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.auth.service.domain.vo.Role;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationEntity;
import com.garamgaebee.service.dataaccess.authentication.entity.AuthenticationRoleEntity;
import com.garamgaebee.service.dataaccess.authentication.repository.AuthenticationJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class AuthenticationRepositoryImplTest {

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    AuthenticationJpaRepository authenticationJpaRepository;

    @Test
    @DisplayName("Authentication 저장 테스트")
    void persistAuthentication() {
        // given
        UUID memberId = UUID.randomUUID();
        String oauthId = "persist-test-id";
        List<Role> roles = List.of(Role.ROLE_GUEST);
        Authentication authentication = Authentication.builder()
                .memberId(memberId)
                .oauthId(oauthId)
                .roles(roles)
                .build();

        // when
        Authentication persistAuthentication = authenticationRepository.persistAuthentication(authentication);

        // then
        Assertions.assertThat(memberId.equals(persistAuthentication.getMemberId()));
        Assertions.assertThat(oauthId.equals(persistAuthentication.getOauthId()));
        Assertions.assertThat(roles.contains(persistAuthentication.getRoles().get(0)));

    }

    @Test
    @DisplayName("oauthId로 read 테스트")
    void findAuthenticationByOauthId() {
        // given
        UUID memberId = UUID.randomUUID();
        String oauthId = "oauth-read-test-id";
        AuthenticationRoleEntity role = AuthenticationRoleEntity.builder()
                        .role(Role.ROLE_GUEST)
                .build();
        AuthenticationEntity authenticationEntity = AuthenticationEntity.builder()
                .memberId(memberId)
                .oauthId(oauthId)
                .build();
        authenticationEntity.addRole(role);

        authenticationJpaRepository.save(authenticationEntity);

        // when
        Optional<Authentication> authentication = authenticationRepository.findAuthenticationByOauthId(oauthId);

        // then
        Assertions.assertThat(authentication.isPresent());
        Assertions.assertThat(authentication.get().getMemberId().equals(memberId));
    }
}