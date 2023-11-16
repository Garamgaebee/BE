package com.garamgaebee.service.dataaccess.notification.entity;

import com.garamgaebee.notification.service.domain.entity.Member;
import com.garamgaebee.notification.service.domain.vo.PushSetting;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private UUID ownerId;

    @Column(nullable = false)
    private Boolean isPushNewFunctionEvent;
    @Column(nullable = false)
    private Boolean isPushTeamEvent;
    @Column(nullable = false)
    private Boolean isPushThreadEvent;
    @Column(nullable = false)
    private Boolean isPushHotThreadEvent;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberNotificationEntity> memberNotificationEntityList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FcmTokenEntity> fcmTokenEntityList = new ArrayList<>();

    public void setPushSetting(PushSetting pushSetting) {
        setIsPushNewFunctionEvent(pushSetting.getIsPushNewFunctionEvent());
        setIsPushTeamEvent(pushSetting.getIsPushTeamEvent());
        setIsPushThreadEvent(pushSetting.getIsPushThreadEvent());
        setIsPushHotThreadEvent(pushSetting.getIsPushHotThreadEvent());
    }

    // 연관관계 메서드
    public void addFcmTokenEntity(FcmTokenEntity fcmToken) {
        if(!fcmTokenEntityList.contains(fcmToken)) {
            fcmTokenEntityList.add(fcmToken);
        }

        fcmToken.setMember(this);
    }

    // 연관관계 메서드
    public void addMemberNotificationEntity(MemberNotificationEntity memberNotification) {
        if(!memberNotificationEntityList.contains(memberNotification)) {
            memberNotificationEntityList.add(memberNotification);
        }

        memberNotification.setMember(this);
    }

    // Domain entity 변환
    public Member ofDomainEntity() {
        return Member.builder()
                .id(this.id)
                .ownerId(this.ownerId)
                .pushSetting(
                        PushSetting.builder()
                                .isPushNewFunctionEvent(this.isPushNewFunctionEvent)
                                .isPushTeamEvent(this.isPushTeamEvent)
                                .isPushThreadEvent(this.isPushThreadEvent)
                                .isPushHotThreadEvent(this.isPushHotThreadEvent)
                                .build()
                )
                .fcmTokenList(
                        this.fcmTokenEntityList.stream().map(fcmToken -> {
                            return fcmToken.ofDomainEntity();
                        }).collect(Collectors.toList())
                )
                .build();
    }

}
