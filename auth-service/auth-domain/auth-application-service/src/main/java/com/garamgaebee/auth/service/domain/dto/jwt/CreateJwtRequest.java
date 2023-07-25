package com.garamgaebee.auth.service.domain.dto.jwt;

import com.garamgaebee.auth.service.domain.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateJwtRequest {
    private UUID memberId;
    private List<Role> roles;
}
