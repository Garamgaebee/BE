package com.garamgaebee.thread.domain.ports.out;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "team-service")
public interface TeamFeignPublisher {
    //TODO TeamVO 객체 가져오기
}
