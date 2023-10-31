package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.vo.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class NotificationDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String moveTo;
    private Boolean isRead;

    @Builder.Default
    @OneToMany(mappedBy = "notificationDetail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberNotificationEntity> memberNotificationEntityList = new ArrayList<>();


    // 연관관계 메서드
    public void addMemberNotification(MemberNotificationEntity memberNotificationEntity) {
        if(!memberNotificationEntityList.contains(memberNotificationEntity)) {
            memberNotificationEntityList.add(memberNotificationEntity);
        }

        memberNotificationEntity.setNotificationDetail(this);
    }

}
