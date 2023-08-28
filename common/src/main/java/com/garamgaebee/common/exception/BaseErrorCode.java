package com.garamgaebee.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BaseErrorCode {
    NOT_FOUND_TEAM(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), "팀을 찾을 수 없습니다.");
    private final boolean isSuccess;
    private final String code;
    private final String message;

}