package com.garamgaebee.teamdomainservice;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.entity.Thread;

import java.util.List;
import java.util.UUID;

public interface TeamDomainService {
    List<UUID> getThreadMemberListbyThreadList(List<Thread> threadList);

    void setTeam(Team team, List<Member> member, List<Thread> threadList, List<Member> threadMemberList);

    void initNotification(Notification notification,Team team);
}
