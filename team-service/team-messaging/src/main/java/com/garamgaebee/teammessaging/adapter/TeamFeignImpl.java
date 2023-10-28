package com.garamgaebee.teammessaging.adapter;

import com.garamgaebee.teamapplicationservice.ports.output.TeamFeign;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teammessaging.image.TeamFeignImagePublisher;
import com.garamgaebee.teammessaging.mapper.TeamFeignDataMapper;
import com.garamgaebee.teammessaging.member.TeamFeignMemberPublisher;
import com.garamgaebee.teammessaging.thread.TeamFeignThreadPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class TeamFeignImpl implements TeamFeign {
    private final TeamFeignThreadPublisher teamFeignThreadPublisher;
    private final TeamFeignMemberPublisher teamFeignMemberPublisher;
    private final TeamFeignDataMapper teamFeignDataMapper;
    private final TeamFeignImagePublisher teamFeignImagePublisher;
    @Override
    public List<Member> mainPageMemberFindById(List<UUID> memberIdList) {
//        List<FeignMember> feignMemberList = teamFeignMemberPublisher.getMember(new GetMainPageMemberFeign(memberIdList));
//        return teamFeignDataMapper.feignMemberToMember(feignMemberList);
        return null;
    }

    @Override
    public List<Thread> mainPageThreadFindByIdOrderByDateDesc(UUID value) {
//        List<FeignThread> feignThreadList = teamFeignThreadPublisher.getThread(value,"date",2);
//        return teamFeignDataMapper.feignThreadToThread(feignThreadList);
        return null;
    }

    @Override
    public List<Image> imageSaveByMultipartList(List<MultipartFile> multipartFileList) {
        return teamFeignDataMapper.multipartFileListToImageList(teamFeignImagePublisher.createImage(multipartFileList));
    }

    @Override
    public Member findById(String string) {
        log.info("hi");
        return teamFeignDataMapper.feignMemberToMember(teamFeignMemberPublisher.getMember(string));
    }
}