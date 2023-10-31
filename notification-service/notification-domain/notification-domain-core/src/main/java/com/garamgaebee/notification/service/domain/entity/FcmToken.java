package com.garamgaebee.notification.service.domain.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FcmToken {
    private Long id;
    private String fcmToken;
}
