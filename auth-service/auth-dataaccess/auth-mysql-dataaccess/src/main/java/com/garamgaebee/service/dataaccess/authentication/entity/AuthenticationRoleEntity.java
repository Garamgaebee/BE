package com.garamgaebee.service.dataaccess.authentication.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "role")
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
}
