package com.garamgaebee.thread.domain.ports.in;

import com.garamgaebee.thread.domain.dto.CreateThreadCommand;
import com.garamgaebee.thread.domain.entity.Thread;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface ThreadService {
    Thread createThread(List<MultipartFile> fileList, CreateThreadCommand req);
}
