package com.garamgaebee.teammessaging.thread;

import com.garamgaebee.teammessaging.thread.dto.FeignThread;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient("threadservice")
public interface TeamFeignThreadPublisher {
    @GetMapping(value = "/api/thread/feign/{threadId}", consumes = "application/json")
    List<FeignThread> getThread(@PathVariable UUID threadId, @RequestParam String sort, @RequestParam int limit);
}
