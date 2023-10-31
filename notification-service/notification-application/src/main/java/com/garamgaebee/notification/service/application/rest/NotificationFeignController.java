package com.garamgaebee.notification.service.application.rest;

import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.notification.service.domain.dto.RegisterNotificationCommand;
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

}
