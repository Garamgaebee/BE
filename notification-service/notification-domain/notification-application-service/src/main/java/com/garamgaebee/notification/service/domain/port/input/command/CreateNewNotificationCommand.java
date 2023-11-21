package com.garamgaebee.notification.service.domain.port.input.command;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewNotificationCommand {
    private String title;
    private String body;
    private String linkUrl;
}
