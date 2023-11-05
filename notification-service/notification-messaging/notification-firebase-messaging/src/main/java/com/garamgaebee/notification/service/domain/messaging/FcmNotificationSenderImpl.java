package com.garamgaebee.notification.service.domain.messaging;

import com.garamgaebee.notification.service.domain.dto.fcm.SendNotificationCommand;
import com.garamgaebee.notification.service.domain.port.output.fcm.FcmNotificationSender;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmNotificationSenderImpl implements FcmNotificationSender {

    private final FirebaseMessaging firebaseMessaging;

    @Async
    @Override
    public void sendNotificationByToken(SendNotificationCommand sendNotificationCommand) {

        Notification notification = Notification.builder()
                .setTitle(sendNotificationCommand.getTitle())
                .setBody(sendNotificationCommand.getBody())
                .build();

        Message message = Message.builder()
                .setToken(sendNotificationCommand.getToken())
                .setNotification(notification)
                .putAllData(sendNotificationCommand.getData())
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            //TODO log 찍기
        }

    }
}
