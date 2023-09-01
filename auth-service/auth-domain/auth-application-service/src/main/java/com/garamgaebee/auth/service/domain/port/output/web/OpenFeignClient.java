package com.garamgaebee.auth.service.domain.port.output.web;

import com.garamgaebee.auth.service.domain.dto.create.CreateMemberRequest;

import java.util.UUID;

public interface OpenFeignClient {

    // 회원 등록
    public UUID registerMember(CreateMemberRequest createMemberRequest);

    // 닉네임 중복 체크
    public Boolean checkDuplicateNickname(String nickname);

    // 회원 탈퇴
    public Boolean exitMember(UUID memberId);
}
