package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.mail.CheckAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.mail.RegisterAuthorizationCodeCommand;
import com.garamgaebee.auth.service.domain.dto.mail.SendMailCommand;
import com.garamgaebee.auth.service.domain.port.output.mail.MailClient;
import com.garamgaebee.auth.service.domain.port.output.redis.AuthorizationCodeRedisRepository;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthorizationCodeHandler {

    private final AuthorizationCodeRedisRepository authorizationCodeRedisRepository;

    private final MailClient mailClient;

    private final static int AUTHORIZATION_CODE_LENGTH = 6;
    private final static long AUTHORIZATION_CODE_EXPIRED_TIME = 600000;

    public String createAuthorizationCode(String email) {
        String code = null;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < AUTHORIZATION_CODE_LENGTH; i++) {
                builder.append(random.nextInt(10));
            }
            code =  builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Fail to generate authorization mail code.");
        }

        // redis에 저장
        authorizationCodeRedisRepository.persistAuthorizationCodeWithExpiredTime(RegisterAuthorizationCodeCommand.builder()
                        .email(email)
                        .code(code)
                .build(), AUTHORIZATION_CODE_EXPIRED_TIME);

        return code;
    }

    public void sendAuthorizationCodeToMail(String email, String code) {
        mailClient.sendMail(SendMailCommand.builder()
                        .address(email)
                        .title("[가람개비] 가람개비 인증코드입니다.")
                        .content("가람개비 인증코드는 " + code + " 입니다.")
                .build());
    }

    public Boolean checkAuthorizationCode(CheckAuthorizationCodeCommand checkAuthorizationCodeCommand) {
        return (checkAuthorizationCodeCommand.getCode().equals(
                (authorizationCodeRedisRepository.findAuthorizationCodeByEmail(checkAuthorizationCodeCommand.getEmail())
                        .orElseThrow(() -> new BaseException(BaseErrorCode.WRONG_AUTHORIZATION_EMAIL))
                        .getCode()
                )
            )
        );
    }
}
