package com.garamgaebee.notification.service.domain.dto;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateNotificationCommand {
    private UUID memberId;
    private String title;
    private String body;
    private String linkUrl;
    private NotificationType type;
}
