package com.garamgaebee.auth.service.domain.entity;

import com.garamgaebee.auth.service.domain.vo.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Authentication {

    private Long id;
    private String email;
    private UUID memberId;
    private List<AuthenticationRole> roles;

}
