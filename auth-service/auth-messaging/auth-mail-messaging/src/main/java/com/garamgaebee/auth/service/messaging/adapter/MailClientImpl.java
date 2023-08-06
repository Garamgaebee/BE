package com.garamgaebee.auth.service.messaging.adapter;

import com.garamgaebee.auth.service.domain.dto.mail.SendMailCommand;
import com.garamgaebee.auth.service.domain.port.output.mail.MailClient;
import com.garamgaebee.auth.service.messaging.publisher.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailClientImpl implements MailClient {

    private final EmailSender mailSender;

    @Async
    @Override
    public void sendMail(SendMailCommand sendMailCommand) {
        try {
            mailSender.sendMail(sendMailCommand);
        } catch (Exception e) {
            //TODO 로그로 대체
            System.out.println("fail to send mail");
        }
    }
}
