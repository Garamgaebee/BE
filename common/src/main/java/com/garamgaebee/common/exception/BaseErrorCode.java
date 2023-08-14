package com.garamgaebee.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BaseErrorCode {

    MEMBER_NOT_EXIST(false, Integer.toString(HttpStatus.NOT_FOUND.value()), "유저를 찾을 수 없습니다.");
    EMPTY_IMAGES_URL(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), "이미지 주소 리스트가 비었습니다."),
    EMPTY_IMAGES(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), "이미지 리스트가 비었습니다."),
    SERVER_ERROR(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "서버 오류입니다.");

    private final boolean isSuccess;
    private final String code;
    private final String message;

}