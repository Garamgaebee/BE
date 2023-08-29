package com.garamgaebee.thread.domain.ports.out;

import com.garamgaebee.thread.domain.valueobject.MemberVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "memberservice")
public interface MemberFeignPublisher {

    /**
     * MemberService에서 Member 통째로 가져오기
     * */
    @RequestMapping(method = RequestMethod.GET, value = "/api/feign/member", consumes = "application/json")
    MemberVO getFeignMember(@RequestParam("user-idx") String userIdx);
}
