package com.garamgaebee.service.dataaccess.notification.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.entity.NotificationDetail;
import com.garamgaebee.notification.service.domain.port.output.repository.NotificationRepository;
import com.garamgaebee.service.dataaccess.notification.entity.MemberNotificationEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationDetailEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationFcmTokenEntity;
import com.garamgaebee.service.dataaccess.notification.mapper.MemberNotificationDataAccessMapper;
import com.garamgaebee.service.dataaccess.notification.mapper.NotificationDataAccessMapper;
import com.garamgaebee.service.dataaccess.notification.mapper.NotificationDetailDataAccessMapper;
import com.garamgaebee.service.dataaccess.notification.repository.MemberNotificationJpaRepository;
import com.garamgaebee.service.dataaccess.notification.repository.NotificationDetailJpaRepository;
import com.garamgaebee.service.dataaccess.notification.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationDetailJpaRepository notificationDetailJpaRepository;
    private final MemberNotificationJpaRepository memberNotificationJpaRepository;
    private final NotificationDataAccessMapper notificationDataAccessMapper;
    private final NotificationDetailDataAccessMapper notificationDetailDataAccessMapper;
    private final MemberNotificationDataAccessMapper memberNotificationDataAccessMapper;


    /**
     * 모든 Notification FcmTokenr과 함께 조회
     */
    @Override
    public List<Notification> findAllNotificationFcmToken() {
        return notificationJpaRepository.findAll().stream().map(notificationEntity -> {
            return notificationDataAccessMapper.notificationEntityToNotificationWithoutDetailList(notificationEntity);
        }).collect(Collectors.toList());
    }

    /**
     * NotificationDetail List, FcmToken List를 제외한 Notification 반환
     * push setting과 관련한 조회 시 사용
     */
    @Override
    public Optional<Notification> findNotificationPushSettingByMemberId(UUID memberId) {
        Optional<NotificationEntity> notificationEntityWrapper = notificationJpaRepository.findByMemberId(memberId);

        if(notificationEntityWrapper.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(notificationDataAccessMapper.notificationEntityToNotificationWithoutDetailListAndFcmTokenList(notificationEntityWrapper.get()));
    }

    /**
     * Notification, FcmToken List를 포함한 Notification 객체 반환
     * push 알림 전송 시 사용
     */
    @Override
    public Optional<Notification> findNotificationFcmTokenByMemberId(UUID memberId) {
        Optional<NotificationEntity> notificationEntityWrapper = notificationJpaRepository.findByMemberId(memberId);

        if(notificationEntityWrapper.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(notificationDataAccessMapper.notificationEntityToNotificationWithoutDetailList(notificationEntityWrapper.get()));
    }

    /**
     * Push Setting 변경
     */
    @Override
    public void changeNotificationPushSetting(Notification notification) {
        NotificationEntity notificationEntity = notificationJpaRepository.findById(notification.getId())
                .orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        notificationEntity.setPushSetting(notification.getPushSetting());

        notificationJpaRepository.save(notificationEntity);
    }

    /**
     * 새로운 Notification 등록
     */
    @Override
    public void createNewNotification(Notification notification) {
        NotificationEntity notificationEntity = NotificationEntity.builder()
                .memberId(notification.getMemberId())
                .build();

        notificationEntity.setPushSetting(notification.getPushSetting());
        for(FcmToken fcmToken : notification.getFcmTokenList()) {
            notificationEntity.addNotificationFcmTokenEntity(
                    NotificationFcmTokenEntity.builder()
                            .id(fcmToken.getId())
                            .fcmToken(fcmToken.getFcmToken())
                            .time(fcmToken.getTime())
                            .build()
            );
        }

        notificationJpaRepository.save(notificationEntity);
    }

    /**
     * 새로운 NotificationDetail 생성
     */
    @Override
    public NotificationDetail createNewNotificationDetail(NotificationDetail notificationDetail) {

        return notificationDetailDataAccessMapper.notificationDetailEntityToNotificationDetail(
                notificationDetailJpaRepository.save(
                notificationDetailDataAccessMapper.notificationDetailToNotificationDetailEntity(notificationDetail)
            )
        );
    }

    /**
     * MemberNotification 생성
     */
    @Override
    public void createMemberNotification(Notification notification, NotificationDetail notificationDetail) {
        NotificationEntity notificationEntity = notificationJpaRepository.findById(notification.getId()).orElseThrow(() ->
                new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));
        NotificationDetailEntity notificationDetailEntity = notificationDetailJpaRepository.findById(notificationDetail.getId()).orElseThrow(() ->
                new BaseException(BaseErrorCode.NOTIFICATION_NOT_EXIST));

        memberNotificationJpaRepository.save(MemberNotificationEntity.builder()
                .notification(notificationEntity)
                .notificationDetail(notificationDetailEntity)
                .build());
    }

    /**
     * MemberNotification 리스트 조회
     */
    public List<MemberNotification> findMemberNotificationList(Notification notification) {
        NotificationEntity notificationEntity = notificationJpaRepository.findById(notification.getId()).orElseThrow(() ->
                new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        return notificationEntity.getMemberNotificationEntityList().stream().map(memberNotificationEntity -> {
            return memberNotificationDataAccessMapper.memberNotificationEntityToMemberNotification(memberNotificationEntity);
        }).collect(Collectors.toList());
    }

    /**
     * MemberNotification 단건 조회
     */
    @Override
    public Optional<MemberNotification> findMemberNotification(Long memberNotificationId) {
        Optional<MemberNotificationEntity> memberNotificationEntityWrapper = memberNotificationJpaRepository.findById(memberNotificationId);

        if(memberNotificationEntityWrapper.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(memberNotificationDataAccessMapper.memberNotificationEntityToMemberNotification(memberNotificationEntityWrapper.get()));
    }

    /**
     * memberNotification 저장
     */
    @Override
    public void saveMemberNotification(MemberNotification memberNotification) {
        MemberNotificationEntity memberNotificationEntity = memberNotificationJpaRepository.findById(memberNotification.getId()).orElseThrow(
                () -> new BaseException(BaseErrorCode.NOTIFICATION_NOT_EXIST)
        );

        memberNotificationEntity.setIsRead(memberNotification.getIsRead());
        memberNotificationJpaRepository.save(memberNotificationEntity);
    }


}
