package com.garamgaebee.thread.domain;

import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.ports.out.ImageFeignPublisher;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import com.garamgaebee.thread.domain.valueobject.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateThreadHelper {

    private final ThreadRepository repository;

    private final ImageFeignPublisher imageFeignPublisher;

    @Autowired
    public CreateThreadHelper(ThreadRepository repository, ImageFeignPublisher imageFeignPublisher) {
        this.repository = repository;
        this.imageFeignPublisher = imageFeignPublisher;
    }

    public Thread createThread(Thread createTarget) {
        return repository.createThread(createTarget);
    }

    /**
     * 이미지 파일 리스트에서 url 리스트로 바꾸기
     * */
    public List<String> getImageList(List<MultipartFile> fileList) {
        ImageVO imageList = imageFeignPublisher.getFeignImageUrls(fileList);
        List<String> imgUrls = new ArrayList<>();
        if(!imageList.getUrl().isEmpty()) imgUrls.addAll(imageList.getUrl());

        return imgUrls;
    }
}
