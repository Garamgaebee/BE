package com.garamgaebee.teammessaging.member;

import com.garamgaebee.teamapplicationservice.dto.feign.GetMainPageMemberFeign;
import com.garamgaebee.teammessaging.member.dto.FeignMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("member-service")
public interface TeamFeignMemberPublisher {
    @GetMapping(value = "/api/member/feign", consumes = "application/json")
    List<FeignMember> getMember(@RequestBody GetMainPageMemberFeign getMainPageMemberFeign);
}