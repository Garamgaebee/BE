package com.garamgaebee.teamapplicationservice.ports.output;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface TeamFeign {
    List<Member> mainPageMemberFindById(List<UUID> memberIdList);

    List<Thread> mainPageThreadFindByIdOrderByDateDesc(UUID value);
    List<Image> imageSaveByMultipartList(List<MultipartFile> multipartFileList);
}
