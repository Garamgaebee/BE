package com.garamgaebee.thread.messaging.listener;

import com.garamgaebee.thread.domain.ports.in.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/feign/threads")
public class ThreadFeignListener {
    private final ThreadService threadService;

    @Autowired
    public ThreadFeignListener(ThreadService threadService) {
        this.threadService = threadService;
    }
}
