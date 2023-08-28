package com.garamgaebee.service.dataaccess.authentication.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder
public abstract class AuthenticationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String email;
    @Setter
    private UUID memberId;
    @Builder.Default // 빌더패턴 사용 시 자동초기화되므로 지정한 값으로 초기화 될 수 있게 처리
    @OneToMany(mappedBy = "authentication", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AuthenticationRoleEntity> roles = new ArrayList<AuthenticationRoleEntity>();

    public void addRole(AuthenticationRoleEntity authenticationRoleEntity) {
        this.roles.add(authenticationRoleEntity);
        authenticationRoleEntity.setAuthentication(this);
    }
}
