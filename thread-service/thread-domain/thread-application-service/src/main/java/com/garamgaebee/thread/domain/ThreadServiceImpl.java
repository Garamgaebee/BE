package com.garamgaebee.thread.domain;

import com.garamgaebee.thread.domain.dto.CreateThreadCommand;
import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@ComponentScan
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public Thread createThread(List<MultipartFile> fileList, CreateThreadCommand req) {
        //TODO 이미지 url로 바꾸기
        List<String> imgUrls = new ArrayList<>();
        String memberProfileUrl = "test";
        String teamProfileUrl = "test1";

        Thread createTarget = Thread.builder()
                .authorIdx(req.getAuthorIdx())
                .content(req.getContent())
                .isComment(false)
                .parentIdx("None")
                .imgUrls(imgUrls)
                .memberProfileImg(memberProfileUrl)
                .teamProfileImg(teamProfileUrl)
                .build();

        return threadRepository.createThread(createTarget);
    }
}
