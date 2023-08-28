package com.garamgaebee.auth.service.domain.port.output.mail;

import com.garamgaebee.auth.service.domain.dto.mail.SendMailCommand;

public interface MailClient {

    public void sendMail(SendMailCommand sendMailCommand);
}
