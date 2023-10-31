package com.garamgaebee.service.dataaccess.notification.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.entity.NotificationDetail;
import com.garamgaebee.notification.service.domain.port.output.repository.NotificationRepository;
import com.garamgaebee.service.dataaccess.notification.entity.MemberNotificationEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationDetailEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationFcmTokenEntity;
import com.garamgaebee.service.dataaccess.notification.mapper.NotificationDataAccessMapper;
import com.garamgaebee.service.dataaccess.notification.mapper.NotificationDetailDataAccessMapper;
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
    private final NotificationDataAccessMapper notificationDataAccessMapper;
    private final NotificationDetailDataAccessMapper notificationDetailDataAccessMapper;

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
                            .build()
            );
        }

        notificationJpaRepository.save(notificationEntity);
    }

    /**
     * 새로운 NotificationDetail 생성
     */
    @Override
    public void createNewNotificationDetail(NotificationDetail notificationDetail, List<UUID> memberIdList) {

        NotificationDetailEntity notificationDetailEntity = notificationDetailDataAccessMapper.notificationDetailToNotificationDetailEntity(notificationDetail);

        try {
            for (UUID memberId : memberIdList) {
                NotificationEntity notificationEntity = notificationJpaRepository.findByMemberId(memberId).orElseThrow(() ->
                        new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

                MemberNotificationEntity.builder()
                        .notification(notificationEntity)
                        .notificationDetail(notificationDetailEntity)
                        .build();

                notificationJpaRepository.save(notificationEntity);
            }
        } catch (BaseException e) {
            //TODO 없는 member id log 찍기
        }

    }

    /**
     * 신기능 알림 허용 멤버 조회
     */
    @Override
    public List<Notification> findAllNotificationByAllowNewEventPush() {
        List<NotificationEntity> notificationEntityList = notificationJpaRepository.findAllByIsPushNewFunctionEventIsTrue();

        return notificationEntityList.stream().map(notificationEntity -> {
            return notificationDataAccessMapper.notificationEntityToNotificationWithoutDetailList(notificationEntity);
        }).collect(Collectors.toList());
    }

    /**
     * 인기 게시글 알림 허용 멤버 조회
     */
    @Override
    public List<Notification> findAllNotificationByAllowHotThreadEventPush() {
        List<NotificationEntity> notificationEntityList = notificationJpaRepository.findAllByIsPushHotThreadEventIsTrue();

        return notificationEntityList.stream().map(notificationEntity -> {
            return notificationDataAccessMapper.notificationEntityToNotificationWithoutDetailList(notificationEntity);
        }).collect(Collectors.toList());
    }


}
