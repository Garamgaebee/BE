package com.garamgaebee.thread.domain;

import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateThreadHelper {

    private final ThreadRepository repository;

    @Autowired
    public CreateThreadHelper(ThreadRepository repository) {
        this.repository = repository;
    }

    public Thread createThread(Thread createTarget) {
        return repository.createThread(createTarget);
    }
}
