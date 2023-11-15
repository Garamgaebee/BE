package com.garamgaebee.notification.service.domain.entity;

import com.garamgaebee.notification.service.domain.vo.PushSetting;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Member {
    private Long id;
    @Setter
    private UUID ownerId;
    @Setter
    private PushSetting pushSetting;
    @Setter
    private List<FcmToken> fcmTokenList;

    // entity 생성
    public static Member create(UUID ownerId) {
        return Member.builder()
                .id(null)
                .ownerId(ownerId)
                .pushSetting(PushSetting.builder()
                        .isPushNewFunctionEvent(false)
                        .isPushTeamEvent(false)
                        .isPushHotThreadEvent(false)
                        .isPushThreadEvent(false)
                        .build())
                .fcmTokenList(new ArrayList<>())
                .build();
    }

    // 새 기능 알림 설정 변경
    public void changeNewFunctionPush() {
        this.pushSetting.changeIsPushNewFunctionEventStatus();
    }

    // 팀 알림 설정 변경
    public void changeTeamPush() {
        this.pushSetting.changeIsPushTeamEventStatus();
    }

    // 스레드 알림 설정 변경
    public void changeThreadPush() {
        this.pushSetting.changeIsPushThreadEventStatus();
    }

    // 인기 스레드 알림 설정 변경
    public void changeHotThreadPush() {
        this.pushSetting.changeIsPushHotThreadEventStatus();
    }

    // fcm token 추가
    public void addFcmToken(FcmToken fcmToken) {
        fcmTokenList.add(fcmToken);
    }

}
