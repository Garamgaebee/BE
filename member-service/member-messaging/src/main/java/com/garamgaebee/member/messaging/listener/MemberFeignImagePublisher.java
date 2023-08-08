package com.garamgaebee.member.messaging.listener;

import com.garamgeabee.member.domain.dto.SaveImageUrl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "imageservice", url = "localhost:8080/api/feign/image")
public interface MemberFeignImagePublisher {
    //TODO 이미지 저장 삭제 Feign 바인딩
}

