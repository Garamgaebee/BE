package com.garamgaebee.notification.service.domain.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class MemberNotification {
    private Long id;
    @Setter
    private Long memberId;
    @Setter
    private Notification notification;
    @Setter
    private Boolean isRead;

    // entity 생성
    public static MemberNotification create(Long memberId, Notification notification) {
        MemberNotification memberNotification = MemberNotification.builder()
                .id(null)
                .memberId(memberId)
                .notification(notification)
                .isRead(false)
                .build();
        // notification 객체에 자신 추가
        notification.getMemberNotificationList().add(memberNotification);

        return memberNotification;
    }

    // 읽음 처리
    public void read() {
        this.setIsRead(true);
    }
}
