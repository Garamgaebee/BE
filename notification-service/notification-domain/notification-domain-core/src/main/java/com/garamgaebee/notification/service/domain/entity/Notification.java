package com.garamgaebee.notification.service.domain.entity;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Notification {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String body;
    @Setter
    private LocalDateTime time;
    @Setter
    private NotificationType type;
    @Setter
    private String moveTo;
    @Setter
    private List<MemberNotification> memberNotificationList;

    // entity 생성
    public static Notification create(String title, String body, NotificationType type, String moveTo) {
        return Notification.builder()
                .id(null)
                .title(title)
                .body(body)
                .time(LocalDateTime.now())
                .type(type)
                .moveTo(moveTo)
                .memberNotificationList(new ArrayList<>())
                .build();
    }

}
