package com.garamgaebee.notification.service.domain.port.input.command;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class RegisterMemberCommand {
    private UUID ownerId;
    private String fcmToken;
}
