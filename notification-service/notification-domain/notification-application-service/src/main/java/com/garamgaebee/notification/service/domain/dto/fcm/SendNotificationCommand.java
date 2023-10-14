package com.garamgaebee.notification.service.domain.dto.fcm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class SendNotificationCommand {
    private String token;
    private String title;
    private String body;
    private Map<String, String> data;

    @Builder
    public SendNotificationCommand(String token, String title, String body, Map<String, String> data) {
        this.token = token;
        this.title = title;
        this.body = body;
        this.data = data;
    }
}
