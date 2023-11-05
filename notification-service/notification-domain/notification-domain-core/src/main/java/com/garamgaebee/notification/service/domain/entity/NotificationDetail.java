package com.garamgaebee.notification.service.domain.entity;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDetail {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime time;
    //TODO enum으로 변경
    private NotificationType type;
    private String moveTo;

}
