package com.garamgaebee.auth.service.domain.dto.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class DeleteMemberCommand {
    private UUID memberId;
    private String refreshToken;
}
