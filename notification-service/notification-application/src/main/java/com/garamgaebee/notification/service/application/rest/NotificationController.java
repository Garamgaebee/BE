package com.garamgaebee.notification.service.application.rest;

import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.notification.service.domain.dto.GetMemberNotificationResponse;
import com.garamgaebee.notification.service.domain.dto.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.port.input.service.NotificationApplicationService;
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

    private final NotificationApplicationService notificationApplicationService;

    /**
     * push 알림 설정정보 조회
     */
    @GetMapping("/{member-id}/info")
    public BaseResponse<GetNotificationResponse> getMemberNotificationSetting(@PathVariable("member-id")UUID memberId) {
        return new BaseResponse<>(notificationApplicationService.getNotificationSettingInfo(memberId));
    }

    /**
     * new event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/new-event")
    public BaseResponse<Boolean> changeNewEventPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(notificationApplicationService.changePushNewEventNotificationStatus(memberId));
    }

    /**
     * team event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/team")
    public BaseResponse<Boolean> changeTeamPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(notificationApplicationService.changePushTeamEventNotificationStatus(memberId));
    }

    /**
     * thread event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/thread")
    public BaseResponse<Boolean> changeThreadPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(notificationApplicationService.changePushThreadEventNotificationStatus(memberId));
    }

    /**
     * hot thread event push 설정 변경
     */
    @PatchMapping("/{member-id}/setting/hot-thread")
    public BaseResponse<Boolean> changeHotThreadPushSetting(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(notificationApplicationService.changePushHotThreadEventNotificationStatus(memberId));
    }

    /**
     * member 알림 목록 조회
     */
    @GetMapping("/{member-id}")
    public BaseResponse<List<GetMemberNotificationResponse>> getMemberNotificationList(@PathVariable("member-id") UUID memberId) {
        return new BaseResponse<>(notificationApplicationService.getMemberNotificationList(memberId));
    }


    /**
     * 알림 읽음 처리
     */
}
