package com.garamgaebee.auth.service.messaging.publisher;

import com.garamgaebee.auth.service.domain.dto.mail.SendMailCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSenderTest {

    @Autowired
    private EmailSender mailSender;

    @Test
    @DisplayName("메일 전송 테스트")
    void sendMail() {
        // given
        SendMailCommand sendMailCommand = SendMailCommand.builder()
                .address("tjrcjs0705@gmail.com")
                .title("test mail")
                .content("test mail content body")
                .build();

        // when
        mailSender.sendMail(sendMailCommand);

    }
}