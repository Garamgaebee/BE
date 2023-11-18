package com.garamgaebee.notification.service.application.rest;

import com.garamgaebee.common.response.BaseResponse;
import com.garamgaebee.notification.service.domain.port.input.CreateMemberUseCase;
import com.garamgaebee.notification.service.domain.port.input.CreateNotificationUseCase;
import com.garamgaebee.notification.service.domain.port.input.DeleteMemberUseCase;
import com.garamgaebee.notification.service.domain.port.input.DeleteSingleFcmTokenUseCase;
import com.garamgaebee.notification.service.domain.port.input.command.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feign/notifications")
public class NotificationFeignController {

    private final CreateMemberUseCase createMemberUseCase;
    private final CreateNotificationUseCase createNotificationUseCase;
    private final DeleteSingleFcmTokenUseCase deleteSingleFcmTokenUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;

    /**
     * 새로운 Notification 객체 등록 : 회원가입 시 call
     */
    @PostMapping("")
    public BaseResponse<Boolean> registerNewMember(@RequestBody RegisterMemberCommand registerMemberCommand) {
        return new BaseResponse<>(createMemberUseCase.createMember(registerMemberCommand));
    }

    /**
     * 신기능 알림 생성
     */
    @PostMapping("/new-event")
    public BaseResponse<Boolean> createNewEventNotification(@RequestBody CreateNewNotificationCommand createNewNotificationCommand) {
        return new BaseResponse<>(createNotificationUseCase.createNewNotification(createNewNotificationCommand));
    }

    /**
     * 팀 관련 알림 생성
     */
    @PostMapping("/team")
    public BaseResponse<Boolean> createTeamNotification(@RequestBody CreateTeamNotificationCommand createTeamNotificationCommand) {
        return new BaseResponse<>(createNotificationUseCase.createTeamNotification(createTeamNotificationCommand));
    }

    /**
     * 스레드 관련 알림 생성
     */
    @PostMapping("/thread")
    public BaseResponse<Boolean> createThreadNotification(@RequestBody CreateThreadNotificationCommand createThreadNotificationCommand) {
        return new BaseResponse<>(createNotificationUseCase.createThreadNotification(createThreadNotificationCommand));
    }

    /**
     * 인기 스레드 관련 알림 생성
     */
    @PostMapping("/hot-thread")
    public BaseResponse<Boolean> createHotThreadNotification(@RequestBody CreateHotThreadNotificationCommand createHotThreadNotificationCommand) {
        return new BaseResponse<>(createNotificationUseCase.createHotThreadNotification(createHotThreadNotificationCommand));
    }

    /**
     * 단일 fcm token 제거 : 로그아웃 시 call
     */
    @DeleteMapping("/tokens/{token}")
    public BaseResponse<Boolean> deleteFcmToken(@PathVariable("token") String fcmToken) {
        return new BaseResponse<>(deleteSingleFcmTokenUseCase.deleteSingleFcmToken(fcmToken));
    }

    /**
     * 멤버 제거 : 회원 탈퇴 시 call
     */
    @DeleteMapping("/{owner-id}")
    public BaseResponse<Boolean> deleteMember(@PathVariable("owner-id") UUID ownerId) {
        return new BaseResponse<>(deleteMemberUseCase.deleteMember(ownerId));
    }

}
