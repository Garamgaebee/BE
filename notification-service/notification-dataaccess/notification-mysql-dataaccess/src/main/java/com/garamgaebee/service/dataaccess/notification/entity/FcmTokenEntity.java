package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.entity.FcmToken;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FcmTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(nullable = false, unique = true)
    private String fcmToken;

    @Column(nullable = false)
    private LocalDateTime time;

    // 연관관계 메서드
    public void setMember(MemberEntity member) {
        this.member = member;

        if(!member.getFcmTokenEntityList().contains(this)) {
            member.addFcmTokenEntity(this);
        }
    }

    // Domain entity 변환
    public FcmToken ofDomainEntity() {
        return FcmToken.builder()
                .id(this.id)
                .fcmToken(this.fcmToken)
                .time(this.time)
                .build();
    }
}
