package com.garamgaebee.notification.service.domain.port.input.command;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateTeamNotificationCommand {
    private String title;
    private String body;
    private String linkUrl;
    private List<UUID> ownerIdList;
}
