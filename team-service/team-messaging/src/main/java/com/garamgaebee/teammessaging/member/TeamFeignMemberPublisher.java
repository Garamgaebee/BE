package com.garamgaebee.teammessaging.member;

import com.garamgaebee.teammessaging.member.dto.GetFeignMemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("member-service")
public interface TeamFeignMemberPublisher {
    @RequestMapping(method = RequestMethod.GET, value = "/api/feign/member", consumes = "application/json")
    GetFeignMemberResponse getMember(@RequestParam("user-idx") String memberId);
}