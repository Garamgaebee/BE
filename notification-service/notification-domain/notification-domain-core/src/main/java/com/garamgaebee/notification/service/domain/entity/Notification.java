package com.garamgaebee.notification.service.domain.entity;

import com.garamgaebee.notification.service.domain.vo.FcmToken;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    private Long id;
    private UUID memberId;
    private PushSetting pushSetting;
    private List<FcmToken> fcmTokenList;
    private List<NotificationDetail> notificationDetailList;


    public void init(UUID memberId, String fcmToken) {
        setMemberId(memberId);
        List<FcmToken> newFcmTokenList = new ArrayList<>();
        newFcmTokenList.add(
                FcmToken.builder()
                        .fcmToken(fcmToken)
                        .build()
        );
        setFcmTokenList(newFcmTokenList);
        setPushSetting(
                PushSetting.builder()
                        .isPushNewFunctionEvent(false)
                        .isPushTeamEvent(false)
                        .isPushThreadEvent(false)
                        .isPushHotThreadEvent(false)
                        .build()
        );
        setNotificationDetailList(new ArrayList<>());
    }

    public void changeIsPushNewFunctionEventStatus() {
        pushSetting.setIsPushNewFunctionEvent(!pushSetting.getIsPushTeamEvent());
    }

    public void changeIsPushTeamEventStatus() {
        pushSetting.setIsPushTeamEvent(!pushSetting.getIsPushTeamEvent());
    }

    public void changeIsPushThreadEventStatus() {
        pushSetting.setIsPushThreadEvent(!pushSetting.getIsPushThreadEvent());
    }

    public void changeIsPushHotThreadEventStatus() {
        pushSetting.setIsPushHotThreadEvent(!pushSetting.getIsPushHotThreadEvent());
    }

    public void addFcmToken(String fcmToken) {
        fcmTokenList.add(
                FcmToken.builder()
                        .fcmToken(fcmToken)
                        .build()
        );
    }

    public void deleteFcmToken(String fcmToken) {
        fcmTokenList.remove(
                FcmToken.builder()
                        .fcmToken(fcmToken)
                        .build()
        );
    }
}
