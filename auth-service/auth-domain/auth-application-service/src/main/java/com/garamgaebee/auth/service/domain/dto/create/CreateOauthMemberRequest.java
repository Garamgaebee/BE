package com.garamgaebee.auth.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOauthMemberRequest {
    private String oauthId;
    private String name;
}
