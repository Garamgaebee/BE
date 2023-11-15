package com.garamgaebee.notification.service.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class FcmToken {
    private Long id;
    @Setter
    private String fcmToken;
    @Setter
    private LocalDateTime time;

    // entity 생성
    public static FcmToken create(String fcmToken, LocalDateTime time) {
        return FcmToken.builder()
                .id(null)
                .fcmToken(fcmToken)
                .time(time)
                .build();
    }

    // 토큰 갱신
    public void renew(LocalDateTime time) {
        this.setTime(time);
    }
}
