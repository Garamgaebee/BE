package com.garamgaebee.teamapplicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateNotificationResponse {
    Boolean isCreate;
    Long notificationId;
    Long teamId;
}
