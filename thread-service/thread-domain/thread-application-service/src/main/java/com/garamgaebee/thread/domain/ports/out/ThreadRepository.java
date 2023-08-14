package com.garamgaebee.thread.domain.ports.out;

import com.garamgaebee.thread.domain.entity.Thread;
import org.springframework.stereotype.Component;

@Component
public interface ThreadRepository {

    Thread createThread(Thread createTarget);
}
