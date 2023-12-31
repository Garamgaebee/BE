package com.garamgaebee.member.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * 회원 생성 Request
 * */
@Getter
@ToString
public class CreateMemberCommand {

    private UUID memberIdx;

    private String nickname;

    private String dept;

    //0: 학생 1: 졸업생
    private int memberType;

    public CreateMemberCommand(UUID memberIdx, String nickname, String dept, int memberType) {
        this.memberIdx = memberIdx;
        this.nickname = nickname;
        this.dept = dept;
        this.memberType = memberType;
    }
}
