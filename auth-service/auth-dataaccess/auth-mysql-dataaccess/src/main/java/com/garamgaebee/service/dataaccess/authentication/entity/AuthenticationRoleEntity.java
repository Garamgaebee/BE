package com.garamgaebee.service.dataaccess.authentication.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "authentication_role")
@Entity
public class AuthenticationRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication_id")
    private AuthenticationEntity authentication;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public AuthenticationRoleEntity(Long id, AuthenticationEntity authentication, Role role) {
        this.id = id;
        this.authentication = authentication;
        this.role = role;
    }

}
