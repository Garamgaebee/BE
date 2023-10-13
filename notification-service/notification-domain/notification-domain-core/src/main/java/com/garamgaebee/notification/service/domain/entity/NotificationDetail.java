package com.garamgaebee.notification.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class NotificationDetail {
    private String title;
    private String body;
    private LocalDateTime time;
    //TODO enum으로 변경
    private String type;
    private String moveTo;
    private Boolean isRead;

}
