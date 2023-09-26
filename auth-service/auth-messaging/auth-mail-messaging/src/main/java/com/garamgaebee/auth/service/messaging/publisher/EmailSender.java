package com.garamgaebee.auth.service.messaging.publisher;

import com.garamgaebee.auth.service.domain.dto.mail.SendMailCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender mailSender;
    public void sendMail(SendMailCommand sendMailCommand) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("garamgaebi22@gmail.com");
        mailMessage.setTo(sendMailCommand.getAddress());
        mailMessage.setSubject(sendMailCommand.getTitle());
        mailMessage.setText(sendMailCommand.getContent());
        mailSender.send(mailMessage);
    }
}
