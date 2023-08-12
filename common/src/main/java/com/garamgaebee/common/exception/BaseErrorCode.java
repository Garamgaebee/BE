package com.garamgaebee.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BaseErrorCode {
    NOT_REGISTERED_MEMBER(false, String.valueOf(HttpStatus.BAD_REQUEST), "등록되지 않은 유저입니다."),
    ALREADY_EXIST_EMAIL(false, String.valueOf(HttpStatus.BAD_REQUEST), "이미 가입된 이메일입니다."),
    ALREADY_EXIST_NICKNAME(false, String.valueOf(HttpStatus.BAD_REQUEST), "이미 사용 중인 닉네임입니다."),
    WRONG_AUTHORIZATION_CODE(false, String.valueOf(HttpStatus.BAD_REQUEST), "인증 코드가 일치하지 않습니다."),
    WRONG_ID_PASSWORD(false, String.valueOf(HttpStatus.BAD_REQUEST), "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_REFRESH_TOKEN(false, String.valueOf(HttpStatus.BAD_REQUEST), "유효하지 않은 Refresh Token입니다."),
    ;
    private final boolean isSuccess;
    private final String code;
    private final String message;

}