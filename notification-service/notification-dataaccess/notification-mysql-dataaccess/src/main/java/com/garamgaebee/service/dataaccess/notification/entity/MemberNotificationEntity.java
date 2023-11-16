package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import com.garamgaebee.notification.service.domain.entity.Notification;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "notification_id")
    private NotificationEntity notification;

    @Column(nullable = false)
    private Boolean isRead;

    @Builder
    public MemberNotificationEntity(MemberEntity member, NotificationEntity notification) {
        setMember(member);
        setNotification(notification);
        setIsRead(false);
    }

    // 연관관계 메서드
    public void setMember(MemberEntity member) {
        this.member = member;

        if(!member.getMemberNotificationEntityList().contains(this)) {
            member.addMemberNotificationEntity(this);
        }
    }

    // 연관관계 메서드
    public void setNotification(NotificationEntity notification) {
        this.notification = notification;

        if(!notification.getMemberNotificationEntityList().contains(this)) {
            notification.addMemberNotification(this);
        }
    }

    // Domain entity 변환
    public MemberNotification ofDomainEntity() {
        return MemberNotification.builder()
                .id(this.id)
                .memberId(this.member.getId())
                .notification(Notification.builder()
                                .id(this.notification.getId())
                                .title(this.notification.getTitle())
                                .body(this.notification.getBody())
                                .time(this.notification.getTime())
                                .type(this.notification.getType())
                                .moveTo(this.notification.getMoveTo())
                                .build())
                .isRead(this.isRead)
                .build();
    }
}
