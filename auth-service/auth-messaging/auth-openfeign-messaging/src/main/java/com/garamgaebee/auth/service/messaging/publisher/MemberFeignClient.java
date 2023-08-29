package com.garamgaebee.auth.service.messaging.publisher;

import com.garamgaebee.auth.service.domain.dto.create.CreateMemberRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("member-service")
public interface MemberFeignClient {

    /**
     * 회원 등록
     */
    @PostMapping("/api/feign/member")
    public UUID registerNewMember(@RequestBody CreateMemberRequest createMemberRequest);

    /**
     * 닉네임 중복 검사
     */
    @GetMapping("/api/feign/member/duplicate")
    public boolean checkDuplicateNickname(@RequestParam("nickname") String nickname);

    //TODO 회원 탈퇴 api call
}
