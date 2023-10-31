package com.garamgaebee.notification.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterNotificationCommand {
    private UUID memberId;
    private String fcmToken;
}
