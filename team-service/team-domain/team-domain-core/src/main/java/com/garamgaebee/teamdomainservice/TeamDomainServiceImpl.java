package com.garamgaebee.teamdomainservice;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.entity.Thread;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class TeamDomainServiceImpl implements TeamDomainService{
    @Override
    public List<UUID> getThreadMemberListbyThreadList(List<Thread> threadList) {
        return threadList.stream().map(
             thread -> thread.getMember().getId().getValue()
        ).collect(Collectors.toList());
    }

    @Override
    public void setTeam(Team team, List<Member> memberList, List<Thread> threadList, List<Member> threadMemberList) {
        team.setMember(memberList);
        threadListSetMemberList(threadList,threadMemberList);
        team.setThreadList(threadList);
    }

    @Override
    public void initNotification(Notification notification, Team team) {
        team.setNotification(notification);
    }

    private void threadListSetMemberList(List<Thread> threadList, List<Member> threadMemberList){
        for(int i=0;i<threadList.size();i++){
            threadList.get(i).setMember(threadMemberList.get(i));
        }
    }
}
