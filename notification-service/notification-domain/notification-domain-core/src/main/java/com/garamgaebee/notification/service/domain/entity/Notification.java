package com.garamgaebee.notification.service.domain.entity;

import com.garamgaebee.notification.service.domain.vo.PushSetting;
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


    public void init(UUID memberId, FcmToken fcmToken) {
        setMemberId(memberId);
        setFcmTokenList(new ArrayList<>());
        setNotificationDetailList(new ArrayList<>());
        addFcmToken(fcmToken);
        setPushSetting(
                PushSetting.builder()
                        .isPushNewFunctionEvent(false)
                        .isPushTeamEvent(false)
                        .isPushThreadEvent(false)
                        .isPushHotThreadEvent(false)
                        .build()
        );
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

    public void addFcmToken(FcmToken fcmToken) {
        fcmTokenList.add(fcmToken);
    }

    public void deleteFcmToken(FcmToken fcmToken) {
        for(FcmToken fcm : fcmTokenList) {
            if(fcm.getId().equals(fcmToken.getId())) {
                fcmTokenList.remove(fcm);
                break;
            }
        }
    }
}
