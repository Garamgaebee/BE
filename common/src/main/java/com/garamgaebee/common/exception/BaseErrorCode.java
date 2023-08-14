package com.garamgaebee.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BaseErrorCode {
    CONTENT_TOO_LONG(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), "내용 길이 초과"),

    MEMBER_NOT_EXIST(false, Integer.toString(HttpStatus.NOT_FOUND.value()), "유저를 찾을 수 없습니다.");
    private final boolean isSuccess;
    private final String code;
    private final String message;

}