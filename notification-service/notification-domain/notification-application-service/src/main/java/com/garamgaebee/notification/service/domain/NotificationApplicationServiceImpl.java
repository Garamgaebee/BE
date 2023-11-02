package com.garamgaebee.notification.service.domain;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.dto.*;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.entity.NotificationDetail;
import com.garamgaebee.notification.service.domain.mapper.NotificationMapper;
import com.garamgaebee.notification.service.domain.port.input.service.NotificationApplicationService;
import com.garamgaebee.notification.service.domain.port.output.fcm.FcmNotificationSender;
import com.garamgaebee.notification.service.domain.port.output.repository.NotificationRepository;
import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;
    private final FcmNotificationSender fcmNotificationSender;

    // 멤버 알림 설정 정보 최초 등록(Notification 객체 생성)
    @Override
    public Boolean registerNewNotification(RegisterNotificationCommand registerNotificationCommand) {
        Notification newNotification = new Notification();
        newNotification.init(
                registerNotificationCommand.getMemberId(),
                registerNotificationCommand.getFcmToken()
        );

        // Notification 정보 저장
        notificationRepository.createNewNotification(newNotification);

        return true;
    }

    // 멤버 알림 설정 정보 조회
    @Override
    public GetNotificationResponse getNotificationSettingInfo(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId)
                .orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        return notificationMapper.NotificationToGetNotificationResponse(notification);
    }

    // 멤버 알림 설정 변경 : 신기능 알림
    @Override
    public Boolean changePushNewEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId)
                .orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushNewFunctionEventStatus();
        // 변경 정보 저장
        notificationRepository.changeNotificationPushSetting(notification);

        return true;
    }

    // 멤버 알림 설정 변경 : 팀 관련 알림
    @Override
    public Boolean changePushTeamEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId)
                .orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushTeamEventStatus();
        // 변경 정보 저장
        notificationRepository.changeNotificationPushSetting(notification);

        return true;
    }

    // 멤버 알림 설정 변경 : 스레드 관련 알림
    @Override
    public Boolean changePushThreadEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId)
                .orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushThreadEventStatus();
        // 변경 정보 저장
        notificationRepository.changeNotificationPushSetting(notification);

        return true;
    }

    // 멤버 알림 설정 변경 : 인기 스레드 관련 알림
    @Override
    public Boolean changePushHotThreadEventNotificationStatus(UUID memberId) {
        Notification notification = notificationRepository.findNotificationPushSettingByMemberId(memberId)
                .orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notification.changeIsPushHotThreadEventStatus();
        // 변경 정보 저장
        notificationRepository.changeNotificationPushSetting(notification);

        return true;
    }

    // 신기능 알림 생성 및 push
    @Override
    public Boolean createNewNotificationDetail(CreateNewNotificationCommand createNewNotificationCommand) {

        // 신기능 알림 허용 멤버 조회
        List<Notification> notificationList = notificationRepository.findAllNotificationFcmToken();
        List<String> fcmTokenList = new ArrayList<>();

        NotificationDetail notificationDetail = NotificationDetail.builder()
                .title(createNewNotificationCommand.getTitle())
                .body(createNewNotificationCommand.getBody())
                .moveTo(createNewNotificationCommand.getLinkUrl())
                .type(NotificationType.SYSTEM)
                .time(LocalDateTime.now())
                .build();

        // 생성된 알림 저장
        notificationDetail = notificationRepository.createNewNotificationDetail(notificationDetail);

        for(Notification notification : notificationList) {

            notificationRepository.createMemberNotification(notification, notificationDetail);

            if(notification.getPushSetting().getIsPushNewFunctionEvent()) {
                for (FcmToken fcmToken : notification.getFcmTokenList()) {
                    fcmTokenList.add(fcmToken.getFcmToken());
                }
            }
        }

        //TODO FCM으로 push

        return true;
    }

    @Override
    public Boolean createTeamNotificationDetail(CreateTeamNotificationCommand createTeamNotificationCommand) {

        // 알림 대상 멤버 fcm 토큰 조회
        List<String> fcmList = new ArrayList<>();

        NotificationDetail notificationDetail = NotificationDetail.builder()
                .title(createTeamNotificationCommand.getTitle())
                .body(createTeamNotificationCommand.getBody())
                .moveTo(createTeamNotificationCommand.getLinkUrl())
                .type(NotificationType.TEAM)
                .time(LocalDateTime.now())
                .build();

        // 생성된 알림 저장
        notificationDetail = notificationRepository.createNewNotificationDetail(notificationDetail);

        for(UUID memberId : createTeamNotificationCommand.getMemberIdList()) {
            try {
                Notification notification = notificationRepository.findNotificationFcmTokenByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

                notificationRepository.createMemberNotification(notification, notificationDetail);

                // team event push 알림을 허용 했다면 fcm token 추출
                if(notification.getPushSetting().getIsPushTeamEvent()) {
                    for (FcmToken fcmToken : notification.getFcmTokenList()) {
                        fcmList.add(fcmToken.getFcmToken());
                    }
                }
            } catch(BaseException e) {
                //TODO 없는 memberId log 찍기
            }
        }

        //TODO FCM으로 push

        return true;

    }

    @Override
    public Boolean createThreadNotificationDetail(CreateThreadNotificationCommand createThreadNotificationCommand) {

        // 알림 대상 멤버 fcm 토큰 조회
        List<String> fcmList = new ArrayList<>();

        NotificationDetail notificationDetail = NotificationDetail.builder()
                .title(createThreadNotificationCommand.getTitle())
                .body(createThreadNotificationCommand.getBody())
                .moveTo(createThreadNotificationCommand.getLinkUrl())
                .type(NotificationType.THREAD)
                .time(LocalDateTime.now())
                .build();

        // 생성된 알림 저장
        notificationDetail = notificationRepository.createNewNotificationDetail(notificationDetail);

        for(UUID memberId : createThreadNotificationCommand.getMemberIdList()) {
            try {
                Notification notification = notificationRepository.findNotificationFcmTokenByMemberId(memberId).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

                notificationRepository.createMemberNotification(notification, notificationDetail);

                // thread event push 알림을 허용 했다면 fcm token 추출
                if(notification.getPushSetting().getIsPushTeamEvent()) {
                    for (FcmToken fcmToken : notification.getFcmTokenList()) {
                        fcmList.add(fcmToken.getFcmToken());
                    }
                }
            } catch(BaseException e) {
                //TODO 없는 memberId log 찍기
            }
        }

        //TODO FCM으로 push

        return true;

    }

    @Override
    public Boolean createHotThreadNotificationDetail(CreateHotThreadNotificationCommand createHotThreadNotificationCommand) {

        // 신기능 알림 허용 멤버 조회
        List<Notification> notificationList = notificationRepository.findAllNotificationFcmToken();
        List<String> fcmTokenList = new ArrayList<>();

        NotificationDetail notificationDetail = NotificationDetail.builder()
                .title(createHotThreadNotificationCommand.getTitle())
                .body(createHotThreadNotificationCommand.getBody())
                .moveTo(createHotThreadNotificationCommand.getLinkUrl())
                .type(NotificationType.HOT_THREAD)
                .time(LocalDateTime.now())
                .build();

        // 생성된 알림 저장
        notificationDetail = notificationRepository.createNewNotificationDetail(notificationDetail);

        for(Notification notification : notificationList) {

            notificationRepository.createMemberNotification(notification, notificationDetail);

            for(FcmToken fcmToken : notification.getFcmTokenList()) {
                fcmTokenList.add(fcmToken.getFcmToken());
            }
        }

        //TODO FCM으로 push

        return true;
    }
}
