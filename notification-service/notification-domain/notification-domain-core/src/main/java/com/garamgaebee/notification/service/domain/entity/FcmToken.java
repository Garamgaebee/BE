package com.garamgaebee.notification.service.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FcmToken {
    private Long id;
    private String fcmToken;
    private LocalDateTime time;
}
