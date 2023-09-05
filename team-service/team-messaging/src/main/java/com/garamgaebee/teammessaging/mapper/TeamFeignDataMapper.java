package com.garamgaebee.teammessaging.mapper;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.valueobject.Department;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teammessaging.image.dto.SaveImageResponse;
import com.garamgaebee.teammessaging.member.dto.FeignMember;
import com.garamgaebee.teammessaging.thread.dto.FeignThread;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamFeignDataMapper {
    public List<Member> feignMemberToMember(List<FeignMember> feignMemberList) {
        return feignMemberList.stream().map(
                feignMember -> Member.builder()
                        .name(feignMember.getName())
                        .department(new Department(feignMember.getDepartment()))
                        .image(new Image(feignMember.getImageUrl()))
                        .build()
        ).collect(Collectors.toList());
    }

    public List<Thread> feignThreadToThread(List<FeignThread> feignThreadList) {
        return feignThreadList.stream().map(
                feignThread -> Thread.builder()
                        .commentCount(feignThread.getCommentCount())
                        .content(feignThread.getContent())
                        .likeCount(feignThread.getLikeCount())
                        .date(feignThread.getDate()).build()
        ).collect(Collectors.toList());
    }

    public List<Image> multipartFileListToImageList(SaveImageResponse saveImageResponse) {
        return saveImageResponse.getUrl().stream().map(
                 s -> Image.builder().url(s).build()
        ).collect(Collectors.toList());
    }
}