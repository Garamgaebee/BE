package com.garamgaebee.thread.dataaccess.thread.adapter;

import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import org.springframework.stereotype.Component;

@Component
public class ThreadRepositoryImpl implements ThreadRepository {
    @Override
    public Thread createThread(Thread createTarget) {
        return null;
    }
}
