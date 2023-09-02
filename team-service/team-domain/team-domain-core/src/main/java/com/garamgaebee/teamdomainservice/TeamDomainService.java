package com.garamgaebee.teamdomainservice;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.entity.Thread;
import com.garamgaebee.teamdomainservice.valueobject.Image;

import java.util.List;
import java.util.UUID;

public interface TeamDomainService {
    List<UUID> getThreadMemberListbyThreadList(List<Thread> threadList);


    void notificationInitAndValidate(Notification notification);

    void notificationSetTeamAndInitNotificationImage(Notification notification, Team team, List<Image> imageUrlList);
}
