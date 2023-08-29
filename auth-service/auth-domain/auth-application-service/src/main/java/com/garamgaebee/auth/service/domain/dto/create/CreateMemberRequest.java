package com.garamgaebee.auth.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateMemberRequest {
    private UUID memberIdx;
    private String nickname;
    private String department;
    //0: 학생 1: 졸업생
    private int memberType;
}
