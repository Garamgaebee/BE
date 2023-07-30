package com.garamgaebee.service.dataaccess.authentication.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "authentication")
@Entity
public class AuthenticationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID memberId;
    private String oauthId;

    @Builder.Default // 빌더패턴 사용 시 자동초기화되므로 지정한 값으로 초기화 될 수 있게 처리
    @OneToMany(mappedBy = "authentication", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AuthenticationRoleEntity> roles = new ArrayList<AuthenticationRoleEntity>();

    public void addRole(AuthenticationRoleEntity authenticationRoleEntity) {
        this.roles.add(authenticationRoleEntity);
        authenticationRoleEntity.setAuthentication(this);
    }
}
