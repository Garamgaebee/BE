package com.garamgaebee.thread.domain;

import com.garamgaebee.thread.domain.ports.in.ThreadService;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ComponentScan
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }
}
