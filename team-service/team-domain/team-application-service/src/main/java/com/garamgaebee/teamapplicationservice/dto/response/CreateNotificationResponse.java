package com.garamgaebee.teamapplicationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateNotificationResponse {
    Boolean isCreate;
    UUID notificationId;
    UUID teamId;
}
