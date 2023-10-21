package com.garamgaebee.notification.service.domain.entity;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
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
    private NotificationType type;
    private String moveTo;
    private Boolean isRead;

}
