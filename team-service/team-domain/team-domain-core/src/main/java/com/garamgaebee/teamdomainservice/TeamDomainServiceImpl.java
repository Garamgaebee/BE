package com.garamgaebee.teamdomainservice;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.Position;
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
    public void notificationInitAndValidate(Notification notification) {
        notification.validateNotification();
        notification.initializeNotification();
    }

    @Override
    public void notificationSetTeamAndInitNotificationImage(Notification notification, Team team, List<Image> imageUrlList) {
        notification.setImageList(imageUrlList);
        team.addNotification(notification);
    }

    @Override
    public void initMemberTeam(Member member, Team team) {
        member.setTeam(team);
    }

    @Override
    public boolean isPossibleDoneTeam(Member member) {
        return member.checkLeader();
    }

    @Override
    public void initMemberPosition(Member member, Position position) {
        member.setPosition(position);
    }

    @Override
    public void validateAndInitEditTeamInfo(Team team) {
        team.validateName(team.getTeamName());
        team.validateIntroduce(team.getIntroduce().getContent());
    }

}
