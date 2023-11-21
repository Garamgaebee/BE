package com.garamgaebee.notification.service.domain;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.notification.service.domain.entity.Member;
import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.port.input.CreateNotificationUseCase;
import com.garamgaebee.notification.service.domain.port.input.GetMemberNotificationListUseCase;
import com.garamgaebee.notification.service.domain.port.input.ReadMemberNotificationUseCase;
import com.garamgaebee.notification.service.domain.port.input.command.*;
import com.garamgaebee.notification.service.domain.port.input.response.GetMemberNotificationResponse;
import com.garamgaebee.notification.service.domain.port.output.fcm.SendNotificationPort;
import com.garamgaebee.notification.service.domain.port.output.persistence.*;
import com.garamgaebee.notification.service.domain.vo.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService implements
        CreateNotificationUseCase, GetMemberNotificationListUseCase, ReadMemberNotificationUseCase {

    private final LoadMemberPort loadMemberPort;
    private final LoadMemberNotificationPort loadMemberNotificationPort;
    private final SaveMemberNotificationPort saveMemberNotificationPort;
    private final SaveNotificationPort saveNotificationPort;
    private final SendNotificationPort sendNotificationPort;
    private final UpdateNotificationStatePort updateNotificationStatePort;

    /**
     * Member 알림 내역 조회
     */
    //TODO 요구사항에 따라 페이징 command 정의
    @Override
    public List<GetMemberNotificationResponse> getMemberNotificationList(UUID ownerId) {
        Member member = loadMemberPort.loadMemberByOwner(ownerId).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        //TODO 요구사항에 따라 페이징
        List<MemberNotification> memberNotificationList = loadMemberNotificationPort.loadMemberNotificationsByMemberId(member.getId());

        // stream 사용하여 response build
        return memberNotificationList.stream().map(memberNotification -> {
            return GetMemberNotificationResponse.builder()
                    .id(memberNotification.getId())
                    .title(memberNotification.getNotification().getTitle())
                    .body(memberNotification.getNotification().getBody())
                    .type(memberNotification.getNotification().getType())
                    .time(memberNotification.getNotification().getTime())
                    .isRead(memberNotification.getIsRead())
                    .moveTo(memberNotification.getNotification().getMoveTo())
                    .build();
        }).collect(Collectors.toList());

    }

    /**
     * 알림 읽음 처리
     */
    @Override
    public Boolean readMemberNotification(Long memberNotificationId) {

        MemberNotification memberNotification = loadMemberNotificationPort.loadMemberNotification(memberNotificationId).orElseThrow(
                () -> new BaseException(BaseErrorCode.NOTIFICATION_NOT_EXIST)
        );

        memberNotification.read();

        saveMemberNotificationPort.updateMemberNotification(memberNotification);

        return true;
    }

    /**
     * 신기능 알림 생성
     */
    @Override
    public Boolean createNewNotification(CreateNewNotificationCommand createNewNotificationCommand) {

        // 발송 대상 : 모든 멤버
        List<Member> memberList = loadMemberPort.loadAllMembers();
        List<String> fcmTokenList = new ArrayList<>();

        // 알림 생성
        Notification notification = Notification.create(
                createNewNotificationCommand.getTitle(),
                createNewNotificationCommand.getBody(),
                NotificationType.SYSTEM,
                createNewNotificationCommand.getLinkUrl()
        );

        // 생성된 알림 저장
        notification = saveNotificationPort.createNotification(notification);

        for(Member member : memberList) {
            MemberNotification.create(member.getId(), notification);

            // 신기능 push 알림 허용한 member의 fcm token 저장
            if(member.getPushSetting().getIsPushNewFunctionEvent()) {
                for (FcmToken fcmToken : member.getFcmTokenList()) {
                    fcmTokenList.add(fcmToken.getFcmToken());
                }
            }
        }

        // 생성된 MemberNotification 저장
        updateNotificationStatePort.updateMemberNotifications(notification);

        // FCM으로 push
        sendNotificationToFcmNotificationSender(notification, fcmTokenList);

        return true;

    }

    /**
     * 팀 알림 생성
     */
    @Override
    public Boolean createTeamNotification(CreateTeamNotificationCommand createTeamNotificationCommand) {

        // 발송 대상 : 지정된 멤버
        List<Member> memberList = loadMemberPort.loadMembersByOwners(createTeamNotificationCommand.getOwnerIdList());
        List<String> fcmTokenList = new ArrayList<>();

        // 알림 생성
        Notification notification = Notification.create(
                createTeamNotificationCommand.getTitle(),
                createTeamNotificationCommand.getBody(),
                NotificationType.TEAM,
                createTeamNotificationCommand.getLinkUrl()
        );

        // 생성된 알림 저장
        notification = saveNotificationPort.createNotification(notification);

        for(Member member : memberList) {
            MemberNotification.create(member.getId(), notification);

            // 팀 push 알림 허용한 member의 fcm token 저장
            if(member.getPushSetting().getIsPushTeamEvent()) {
                for (FcmToken fcmToken : member.getFcmTokenList()) {
                    fcmTokenList.add(fcmToken.getFcmToken());
                }
            }
        }

        // 생성된 MemberNotification 저장
        updateNotificationStatePort.updateMemberNotifications(notification);

        // FCM으로 push
        sendNotificationToFcmNotificationSender(notification, fcmTokenList);

        return true;

    }

    /**
     * 스레드 알림 생성
     */
    @Override
    public Boolean createThreadNotification(CreateThreadNotificationCommand createThreadNotificationCommand) {
        // 발송 대상 : 지정된 멤버
        List<Member> memberList = loadMemberPort.loadMembersByOwners(createThreadNotificationCommand.getOwnerIdList());
        List<String> fcmTokenList = new ArrayList<>();

        // 알림 생성
        Notification notification = Notification.create(
                createThreadNotificationCommand.getTitle(),
                createThreadNotificationCommand.getBody(),
                NotificationType.THREAD,
                createThreadNotificationCommand.getLinkUrl()
        );

        // 생성된 알림 저장
        notification = saveNotificationPort.createNotification(notification);

        for(Member member : memberList) {
            MemberNotification.create(member.getId(), notification);

            // 팀 push 알림 허용한 member의 fcm token 저장
            if(member.getPushSetting().getIsPushThreadEvent()) {
                for (FcmToken fcmToken : member.getFcmTokenList()) {
                    fcmTokenList.add(fcmToken.getFcmToken());
                }
            }
        }

        // 생성된 MemberNotification 저장
        updateNotificationStatePort.updateMemberNotifications(notification);

        // FCM으로 push
        sendNotificationToFcmNotificationSender(notification, fcmTokenList);

        return true;

    }

    /**
     * 인기 스레드 알림 생성
     */
    @Override
    public Boolean createHotThreadNotification(CreateHotThreadNotificationCommand createHotThreadNotificationCommand) {
        // 발송 대상 : 모든 멤버
        List<Member> memberList = loadMemberPort.loadAllMembers();
        List<String> fcmTokenList = new ArrayList<>();

        // 알림 생성
        Notification notification = Notification.create(
                createHotThreadNotificationCommand.getTitle(),
                createHotThreadNotificationCommand.getBody(),
                NotificationType.HOT_THREAD,
                createHotThreadNotificationCommand.getLinkUrl()
        );

        // 생성된 알림 저장
        notification = saveNotificationPort.createNotification(notification);

        for(Member member : memberList) {
            MemberNotification.create(member.getId(), notification);

            // 신기능 push 알림 허용한 member의 fcm token 저장
            if(member.getPushSetting().getIsPushHotThreadEvent()) {
                for (FcmToken fcmToken : member.getFcmTokenList()) {
                    fcmTokenList.add(fcmToken.getFcmToken());
                }
            }
        }

        // 생성된 MemberNotification 저장
        updateNotificationStatePort.updateMemberNotifications(notification);

        // FCM으로 push
        sendNotificationToFcmNotificationSender(notification, fcmTokenList);

        return true;
    }

    /**
     * fcm push 알림 전송 helper : Private
     */
    private void sendNotificationToFcmNotificationSender(Notification notification, List<String> targetList) {

        // fcm multicast 최대 size : 500
        while(targetList.size() > 500) {
            sendNotificationPort.sendNotificationByToken(SendNotificationCommand.builder()
                    .title(notification.getTitle())
                    .body(notification.getBody())
                    .data(Map.of("type", notification.getType().toString(),
                            "moveTo" , notification.getMoveTo(),
                            "time", notification.getTime().toString()))
                    .tokenList(targetList.subList(0, 499))
                    .build());

            // 전송한 token list에서 삭제
            targetList.subList(0, 499).clear();
        }

        if(!targetList.isEmpty()) {
            sendNotificationPort.sendNotificationByToken(SendNotificationCommand.builder()
                    .title(notification.getTitle())
                    .body(notification.getBody())
                    .data(Map.of("type", notification.getType().toString(),
                            "moveTo" , notification.getMoveTo(),
                            "time", notification.getTime().toString()))
                    .tokenList(targetList)
                    .build());
        }

    }
}
