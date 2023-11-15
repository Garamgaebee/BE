package com.garamgaebee.notification.service.domain.port.input.response;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetMemberNotificationResponse {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime time;
    private NotificationType type;
    private String moveTo;
    private Boolean isRead;
}
