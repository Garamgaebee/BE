package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Team extends AggregateRoot<TeamId> {
    //    Authority authority;
    @Builder.Default
    List<Member> teamMember = new ArrayList<>();
    @Builder.Default
    List<Thread> teamThread = new ArrayList<>();
    @Builder.Default
    List<ExternalLink> externalLink = new ArrayList<>();;
    Introduce introduce;
    @Builder.Default
    List<Notification> notificationList = new ArrayList<>();
    Image image;
    String teamName;
    Size size;

    public void setMember(List<Member> memberList) {
        this.teamMember = memberList;
    }

    public void setThreadList(List<Thread> threadList) {
        this.teamThread = threadList;
    }
    public void setSize(Size size){this.size = size;}

    public void addNotification(Notification notification) {
        this.notificationList.add(notification);
    }

}