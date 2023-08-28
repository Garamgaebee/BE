package com.garamgaebee.teamapplicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
@Builder
@AllArgsConstructor
public class CreateNotificationCommand {
    UUID teamId;
    UUID memberId;
}
