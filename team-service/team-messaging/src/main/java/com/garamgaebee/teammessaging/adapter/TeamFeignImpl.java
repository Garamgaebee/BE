package com.garamgaebee.teammessaging.adapter;

import com.garamgaebee.teamapplicationservice.dto.feign.GetMainPageMemberFeign;
import com.garamgaebee.teamapplicationservice.ports.output.TeamFeign;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teammessaging.mapper.TeamFeignDataMapper;
import com.garamgaebee.teammessaging.member.TeamFeignMemberPublisher;
import com.garamgaebee.teammessaging.member.dto.FeignMember;
import com.garamgaebee.teammessaging.thread.dto.FeignThread;
import com.garamgaebee.teammessaging.thread.TeamFeignThreadPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TeamFeignImpl implements TeamFeign {
    TeamFeignThreadPublisher teamFeignThreadPublisher;
    TeamFeignMemberPublisher teamFeignMemberPublisher;
    TeamFeignDataMapper teamFeignDataMapper;
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
}