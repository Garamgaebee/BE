package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.vo.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    private String moveTo;

    @Builder.Default
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberNotificationEntity> memberNotificationEntityList = new ArrayList<>();


    // 연관관계 메서드
    public void addMemberNotification(MemberNotificationEntity memberNotificationEntity) {
        if(!memberNotificationEntityList.contains(memberNotificationEntity)) {
            memberNotificationEntityList.add(memberNotificationEntity);
        }

        memberNotificationEntity.setNotification(this);
    }

    // Domain entity 변환
    public Notification ofDomainEntity() {
        Notification notification = Notification.builder()
                .id(this.id)
                .title(this.title)
                .body(this.body)
                .time(this.time)
                .type(this.type)
                .moveTo(this.moveTo)
                .memberNotificationList(new ArrayList<>())
                .build();

        this.getMemberNotificationEntityList().stream().map(memberNotificationEntity ->
            notification.getMemberNotificationList().add(
                    MemberNotification.builder()
                    .id(memberNotificationEntity.getId())
                    .memberId(memberNotificationEntity.getMember().getId())
                    .isRead(memberNotificationEntity.getIsRead())
                    .notification(notification)
                    .build()
            )
        );

        return notification;
    }

}
