package com.garamgaebee.notification.service.application.rest;

import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.notification.service.domain.dto.*;
import com.garamgaebee.notification.service.domain.port.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feign/notifications")
public class NotificationFeignController {

    private final NotificationApplicationService notificationApplicationService;

    /**
     * 새로운 Notification 객체 등록
     */
    @PostMapping("")
    public BaseResponse<Boolean> registerNewNotification(@RequestBody RegisterNotificationCommand registerNotificationCommand) {
        return new BaseResponse<>(notificationApplicationService.registerNewNotification(registerNotificationCommand));
    }

    /**
     * 신기능 알림 생성
     */
    @PostMapping("/new-event")
    public BaseResponse<Boolean> createNewEventNotification(@RequestBody CreateNewNotificationCommand createNewNotificationCommand) {
        return new BaseResponse<>(notificationApplicationService.createNewNotificationDetail(createNewNotificationCommand));
    }

    /**
     * 팀 관련 알림 생성
     */
    @PostMapping("/team")
    public BaseResponse<Boolean> createTeamNotification(@RequestBody CreateTeamNotificationCommand createTeamNotificationCommand) {
        return new BaseResponse<>(notificationApplicationService.createTeamNotificationDetail(createTeamNotificationCommand));
    }

    /**
     * 스레드 관련 알림 생성
     */
    @PostMapping("/thread")
    public BaseResponse<Boolean> createThreadNotification(@RequestBody CreateThreadNotificationCommand createThreadNotificationCommand) {
        return new BaseResponse<>(notificationApplicationService.createThreadNotificationDetail(createThreadNotificationCommand));
    }

    /**
     * 인기 스레드 관련 알림 생성
     */
    @PostMapping("/hot-thread")
    public BaseResponse<Boolean> createHotThreadNotification(@RequestBody CreateHotThreadNotificationCommand createHotThreadNotificationCommand) {
        return new BaseResponse<>(notificationApplicationService.createHotThreadNotificationDetail(createHotThreadNotificationCommand));
    }

}
