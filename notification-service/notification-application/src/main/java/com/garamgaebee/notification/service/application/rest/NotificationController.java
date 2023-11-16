package com.garamgaebee.notification.service.application.rest;

import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.notification.service.domain.port.input.*;
import com.garamgaebee.notification.service.domain.port.input.response.GetMemberNotificationResponse;
import com.garamgaebee.notification.service.domain.port.input.response.GetNotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final GetPushSettingUseCase getPushSettingUseCase;
    private final ChangePushSettingUseCase changePushSettingUseCase;
    private final GetMemberNotificationListUseCase getMemberNotificationListUseCase;
    private final ReadMemberNotificationUseCase readMemberNotificationUseCase;

    /**
     * push 알림 설정정보 조회
     */
    @GetMapping("/{member-id}/info")
    public BaseResponse<GetNotificationResponse> getMemberNotificationSetting(@PathVariable("member-id")UUID memberId) {
        return new BaseResponse<>(getPushSettingUseCase.getNotificationSettingInfo(memberId));
    }

    /**
     * new event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/new-event")
    public BaseResponse<Boolean> changeNewEventPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(changePushSettingUseCase.changePushNewEventNotificationStatus(memberId));
    }

    /**
     * team event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/team")
    public BaseResponse<Boolean> changeTeamPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(changePushSettingUseCase.changePushTeamEventNotificationStatus(memberId));
    }

    /**
     * thread event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/thread")
    public BaseResponse<Boolean> changeThreadPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(changePushSettingUseCase.changePushThreadEventNotificationStatus(memberId));
    }

    /**
     * hot thread event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/hot-thread")
    public BaseResponse<Boolean> changeHotThreadPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(changePushSettingUseCase.changePushHotThreadEventNotificationStatus(memberId));
    }

    /**
     * member 알림 목록 조회
     */
    @GetMapping("/{member-id}")
    public BaseResponse<List<GetMemberNotificationResponse>> getMemberNotificationList(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(getMemberNotificationListUseCase.getMemberNotificationList(memberId));
    }


    /**
     * 알림 읽음 처리
     */
    @PostMapping("/{notification-id}/read")
    public BaseResponse<Boolean> readMemberNotification(@PathVariable("notification-id") Long notificationId) {
        return new BaseResponse<>(readMemberNotificationUseCase.readMemberNotification(notificationId));
    }
}
