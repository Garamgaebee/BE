package com.garamgaebee.service.dataaccess.notification.adapter;

import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.port.output.repository.NotificationRepository;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import com.garamgaebee.service.dataaccess.notification.mapper.NotificationDataAccessMapper;
import com.garamgaebee.service.dataaccess.notification.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationDataAccessMapper notificationDataAccessMapper;

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
}
