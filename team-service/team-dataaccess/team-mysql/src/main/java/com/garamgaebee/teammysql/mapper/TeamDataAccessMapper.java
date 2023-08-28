package com.garamgaebee.teammysql.mapper;

import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.Introduce;
import com.garamgaebee.teammysql.entity.TeamEntity;
import com.garamgaebee.teammysql.entity.TeamNotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamDataAccessMapper {
    public Team teamEntityToTeam(TeamEntity teamEntity) {
        return Team.builder()
                .teamName(teamEntity.getName())
                .teamImage(teamEntity.getImage())
                .introduce(new Introduce(teamEntity.getIntroduction()))
                .endDate(teamEntity.getCreatedAt().toLocalDate())
                .image(new Image(teamEntity.getImage()))
                .build();
    }

    public TeamNotificationEntity notificationTonotificationEntity(Notification notification) {
        return TeamNotificationEntity.builder()
                .id(notification.getId().getValue())
                .content(notification.getContent())
                .build();
    }
}
