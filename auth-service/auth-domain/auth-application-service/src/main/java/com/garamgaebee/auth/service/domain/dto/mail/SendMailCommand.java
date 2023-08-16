package com.garamgaebee.auth.service.domain.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SendMailCommand {
    private String address;
    private String title;
    private String content;
}
