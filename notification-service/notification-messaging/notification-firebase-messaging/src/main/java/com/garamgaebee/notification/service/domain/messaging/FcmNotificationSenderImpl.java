package com.garamgaebee.notification.service.domain.messaging;

import com.garamgaebee.notification.service.domain.dto.fcm.SendNotificationCommand;
import com.garamgaebee.notification.service.domain.port.input.fcm.FcmEventListener;
import com.garamgaebee.notification.service.domain.port.output.fcm.FcmNotificationSender;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmNotificationSenderImpl implements FcmNotificationSender {

    private final FirebaseMessaging firebaseMessaging;
    private final FcmEventListener fcmEventListener;

    @Async
    @Override
    public void sendNotificationByToken(SendNotificationCommand sendNotificationCommand) {

        Notification notification = Notification.builder()
                .setTitle(sendNotificationCommand.getTitle())
                .setBody(sendNotificationCommand.getBody())
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .setNotification(notification)
                .putAllData(sendNotificationCommand.getData())
                .addAllTokens(sendNotificationCommand.getTokenList())
                .build();

        List<String> failedTokens = new ArrayList<>();

        try {
            BatchResponse response = firebaseMessaging.getInstance().sendMulticast(message);
            if (response.getFailureCount() > 0) {
                List<SendResponse> responses = response.getResponses();
                for (int i = 0; i < responses.size(); i++) {
                    if (responses.get(i).getException().getMessagingErrorCode().equals(MessagingErrorCode.UNREGISTERED) ||
                        responses.get(i).getException().getMessagingErrorCode().equals(MessagingErrorCode.INVALID_ARGUMENT)
                    ) {
                        // 잘못된 토큰 저장
                        failedTokens.add(sendNotificationCommand.getTokenList().get(i));
                    }
                }
            }
        }
        catch(FirebaseMessagingException e) {
                //TODO 알림 전송 실패 로그
        }

        // 잘못된 토큰이 존재하면
        if(!failedTokens.isEmpty()) {
            // 잘못된 토큰 삭제
            fcmEventListener.deleteFcmTokenList(failedTokens);
        }
    }

}
