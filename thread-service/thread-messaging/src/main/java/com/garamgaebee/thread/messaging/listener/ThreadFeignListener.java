package com.garamgaebee.thread.messaging.listener;

import com.garamgaebee.thread.domain.dto.GetFeignTeamThreadsRes;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/feign/thread")
public class ThreadFeignListener {
    private final ThreadService threadService;

    @Autowired
    public ThreadFeignListener(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/team-list")
    public List<GetFeignTeamThreadsRes> getTeamThreads(@RequestParam("team-idx") Long teamIdx) {
        return threadService.getTeamThreads(teamIdx);
    }
}
