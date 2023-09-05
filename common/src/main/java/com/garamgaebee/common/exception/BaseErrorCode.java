package com.garamgaebee.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BaseErrorCode {
    NOT_FOUND_TEAM(false, String.valueOf(HttpStatus.NOT_FOUND.value()), "팀을 찾을 수 없습니다."),

    CONTENT_TOO_LONG(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), "내용 길이 초과"),


    THREAD_NOT_EXIST(false, Integer.toString(HttpStatus.NOT_FOUND.value()), "대상 스레드를 찾을 수 없습니다."),
    MEMBER_NOT_EXIST(false, Integer.toString(HttpStatus.NOT_FOUND.value()), "유저를 찾을 수 없습니다."),
    COMMENT_NOT_EXIST(false, Integer.toString(HttpStatus.NOT_FOUND.value()), "댓글이 존재하지 않습니다."),
    TEAM_THREAD_NOT_EXIST(false, Integer.toString(HttpStatus.NOT_FOUND.value()), "해당 팀에 스레드가 존재하지 않습니다."),

    EMPTY_IMAGES_URL(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), "이미지 주소 리스트가 비었습니다."),
    EMPTY_IMAGES(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), "이미지 리스트가 비었습니다."),
    SERVER_ERROR(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "서버 오류입니다."),
  
    NOT_REGISTERED_MEMBER(false, String.valueOf(HttpStatus.BAD_REQUEST), "등록되지 않은 유저입니다."),
    ALREADY_EXIST_EMAIL(false, String.valueOf(HttpStatus.BAD_REQUEST), "이미 가입된 이메일입니다."),
    ALREADY_EXIST_NICKNAME(false, String.valueOf(HttpStatus.BAD_REQUEST), "이미 사용 중인 닉네임입니다."),
    WRONG_AUTHORIZATION_CODE(false, String.valueOf(HttpStatus.BAD_REQUEST), "인증 코드가 일치하지 않습니다."),
    WRONG_ID_PASSWORD(false, String.valueOf(HttpStatus.BAD_REQUEST), "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_REFRESH_TOKEN(false, String.valueOf(HttpStatus.BAD_REQUEST), "유효하지 않은 Refresh Token입니다."),

    //TEAM
    TEAM_NOTIFICATION_OVER_CHARACTER_LIMIT(false,String.valueOf(HttpStatus.BAD_REQUEST) ,"최대 글자 수를 초과하였습니다." ),
    TEAM_NOTIFICATION_OVER_IMAGE_COUNT_LIMIT(false, String.valueOf(HttpStatus.BAD_REQUEST), "최대 이미지 수를 초과하였습니다."),

    FAIL_TO_DELETE_PROFILE_IMAGE(false, Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), "기존 이미지 삭제에 실패하였습니다. 서버에 문의해주세요"),
    INVALID_ACCESS_TOKEN(false, String.valueOf(HttpStatus.UNAUTHORIZED), "유효하지 않은 Access Token입니다."),
    EMPTY_ACCESS_TOKEN(false, String.valueOf(HttpStatus.UNAUTHORIZED), "Access Token이 포함되지 않은 요청입니다."),
    FORBIDDEN_ACCESS(false, String.valueOf(HttpStatus.FORBIDDEN), "권한이 없는 접근입니다.");

    private final boolean isSuccess;
    private final String code;
    private final String message;

}