package com.garamgaebee.teammessaging.mapper;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.valueobject.Department;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import com.garamgaebee.teammessaging.image.dto.SaveImageResponse;
import com.garamgaebee.teammessaging.member.dto.GetFeignMemberResponse;
import com.garamgaebee.teammessaging.thread.dto.FeignThread;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamFeignDataMapper {
    public Member feignMemberToMember(GetFeignMemberResponse getFeignMemberResponse) {
        return Member.builder()
                .memberId(new MemberId(getFeignMemberResponse.getMemberIdx()))
                .name(getFeignMemberResponse.getNickname())
                .image(new Image(getFeignMemberResponse.getProfileImgUrl()))
                .department(new Department(getFeignMemberResponse.getDept()))
                .build();
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