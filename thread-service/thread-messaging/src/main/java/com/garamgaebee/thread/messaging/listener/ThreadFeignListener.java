package com.garamgaebee.thread.messaging.listener;

import com.garamgaebee.thread.domain.dto.GetFeignTeamThreadsRes;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import com.garamgaebee.thread.domain.ports.out.MemberFeignPublisher;
import com.garamgaebee.thread.domain.valueobject.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feign/thread")
public class ThreadFeignListener {
    private final ThreadService threadService;

    private final MemberFeignPublisher memberFeignPublisher;

    @Autowired
    public ThreadFeignListener(ThreadService threadService, MemberFeignPublisher memberFeignPublisher) {
        this.threadService = threadService;
        this.memberFeignPublisher = memberFeignPublisher;
    }

    @GetMapping("/team-list")
    public List<GetFeignTeamThreadsRes> getTeamThreads(@RequestParam("team-idx") Long teamIdx) {
        return threadService.getTeamThreads(teamIdx);
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<MemberVO> feignTest(@PathVariable String userIdx){
        return ResponseEntity.ok(memberFeignPublisher.getFeignMember(userIdx));
    }
}
