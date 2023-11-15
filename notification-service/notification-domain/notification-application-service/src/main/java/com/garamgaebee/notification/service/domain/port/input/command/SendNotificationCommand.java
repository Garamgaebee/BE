package com.garamgaebee.notification.service.domain.port.input.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class SendNotificationCommand {
    private List<String> tokenList;
    private String title;
    private String body;
    private Map<String, String> data;

    @Builder
    public SendNotificationCommand(List<String> tokenList, String title, String body, Map<String, String> data) {
        this.tokenList = tokenList;
        this.title = title;
        this.body = body;
        this.data = data;
    }
}
