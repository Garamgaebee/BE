package com.garamgaebee.notification.service.domain;

import com.garamgaebee.notification.service.domain.port.input.fcm.FcmEventListener;
import com.garamgaebee.notification.service.domain.port.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmEventListenerImpl implements FcmEventListener {

    private final NotificationRepository notificationRepository;

    @Override
    public void deleteFcmTokenList(List<String> fcmTokenList) {
        notificationRepository.deleteFcmTokenList(fcmTokenList);
    }
}
