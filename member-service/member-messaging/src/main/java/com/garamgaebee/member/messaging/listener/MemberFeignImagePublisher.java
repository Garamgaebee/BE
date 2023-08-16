package com.garamgaebee.member.messaging.listener;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "imageservice", url = "localhost:8080/api/feign/image")
public interface MemberFeignImagePublisher {
    //TODO 이미지 저장 삭제 Feign 바인딩
}

