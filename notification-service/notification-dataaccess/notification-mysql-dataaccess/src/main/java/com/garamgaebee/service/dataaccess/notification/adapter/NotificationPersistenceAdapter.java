package com.garamgaebee.service.dataaccess.notification.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.entity.MemberNotification;
import com.garamgaebee.notification.service.domain.entity.Notification;
import com.garamgaebee.notification.service.domain.port.output.persistence.LoadMemberNotificationPort;
import com.garamgaebee.notification.service.domain.port.output.persistence.SaveMemberNotificationPort;
import com.garamgaebee.notification.service.domain.port.output.persistence.SaveNotificationPort;
import com.garamgaebee.notification.service.domain.port.output.persistence.UpdateNotificationStatePort;
import com.garamgaebee.service.dataaccess.notification.entity.MemberNotificationEntity;
import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import com.garamgaebee.service.dataaccess.notification.repository.MemberNotificationRepository;
import com.garamgaebee.service.dataaccess.notification.repository.MemberRepository;
import com.garamgaebee.service.dataaccess.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements
        LoadMemberNotificationPort, SaveMemberNotificationPort, SaveNotificationPort, UpdateNotificationStatePort {

    private final NotificationRepository notificationRepository;
    private final MemberNotificationRepository memberNotificationRepository;
    private final MemberRepository memberRepository;

    @Override
    public Optional<MemberNotification> loadMemberNotification(Long memberNotificationId) {
        Optional<MemberNotificationEntity> memberNotificationEntity = memberNotificationRepository.findById(memberNotificationId);

        if(memberNotificationEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(memberNotificationEntity.get().ofDomainEntity());
    }

    @Override
    public List<MemberNotification> loadMemberNotificationsByMemberId(Long memberId) {

        List<MemberNotificationEntity> memberNotificationEntityList = memberNotificationRepository.findAllByMemberId(memberId);

        return memberNotificationEntityList.stream().map(memberNotificationEntity -> {
            return memberNotificationEntity.ofDomainEntity();
        }).collect(Collectors.toList());
    }

    @Override
    public MemberNotification updateMemberNotification(MemberNotification memberNotification) {

        MemberNotificationEntity memberNotificationEntity = memberNotificationRepository.findById(memberNotification.getId()).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        memberNotificationEntity.setIsRead(memberNotification.getIsRead());

        return memberNotificationRepository.save(memberNotificationEntity).ofDomainEntity();
    }

    @Override
    public Notification createNotification(Notification notification) {

        return notificationRepository.save(
                NotificationEntity.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .body(notification.getBody())
                        .time(notification.getTime())
                        .type(notification.getType())
                        .moveTo(notification.getMoveTo())
                        .memberNotificationEntityList(new ArrayList<>())
                        .build())
                .ofDomainEntity();
    }

    @Override
    public void updateMemberNotifications(Notification notification) {

        NotificationEntity notificationEntity = notificationRepository.findById(notification.getId()).orElseThrow(
                () -> new BaseException(BaseErrorCode.NOTIFICATION_NOT_EXIST)
        );

        List<MemberNotificationEntity> memberNotificationEntityList = new ArrayList<>();
        for(MemberNotification memberNotification : notification.getMemberNotificationList()) {
            if(memberNotification.getId() == null) {
                memberNotificationEntityList.add(
                        MemberNotificationEntity.builder()
                                .member(memberRepository.findById(memberNotification.getMemberId()).get())
                                .notification(notificationEntity)
                                .build()
                );
            }
        }

        memberNotificationRepository.saveAll(memberNotificationEntityList);
    }
}
