package com.garamgaebee.teammysql.mapper;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.*;
import com.garamgaebee.teammysql.entity.*;
import com.garamgaebee.teammysql.valueobject.PositionData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TeamDataAccessMapper {
    public Team teamEntityToTeam(TeamEntity teamEntity) {
        return Team.builder()
                .teamName(teamEntity.getName())
                .introduce(new Introduce(teamEntity.getIntroduction()))
                .image(new Image(teamEntity.getImage()))
                .build();
    }

    public TeamNotificationEntity notificationTonotificationEntity(Notification notification) {
        return TeamNotificationEntity.builder()
                .id(notification.getId().getValue())
                .content(notification.getContent())
                .build();
    }

    public Team teamEntityToTeamWithAll(TeamEntity teamEntity, List<TeamExternalLinkEntity> teamExternalLinkEntityList, List<TeamMemberEntity> teamMemberEntityList, List<TeamNotificationEntity> teamNotificationEntityList, List<List<TeamNotificationImageEntity>> teamNotificationImageJpaRepositoryList) {
        List<Member> memberList = teamMemberEntityList.stream().map(
                teamMemberEntity -> new Member(new MemberId(teamMemberEntity.getMemberId()),positionToDomainPosition(teamMemberEntity.getPosition()))
        ).toList();

        List<ExternalLink> externalLinkList = teamExternalLinkEntityList.stream().map(
                teamExternalLinkEntity -> new ExternalLink(teamExternalLinkEntity.getLink())
        ).toList();

        List<Notification> notificationList = IntStream.range(0, teamNotificationEntityList.size()).boxed().map(i -> {
            List<Image> imageList = teamNotificationImageJpaRepositoryList.get(i).stream().map(
                    teamNotificationImageEntity -> new Image(teamNotificationImageEntity.getUrl())
            ).toList();
            String content = teamNotificationEntityList.get(i).getContent();
            return Notification.builder()
                    .image(imageList)
                    .content(content).build();
        }).toList();

        return Team.builder()
                .teamMember(memberList)
                .teamName(teamEntity.getName())
                .introduce(new Introduce(teamEntity.getIntroduction()))
                .externalLink(externalLinkList)
                .image(new Image(teamEntity.getImage()))
                .notificationList(notificationList)
                .build();
    }
    public Position positionToDomainPosition(PositionData positionData){
        if (positionData ==PositionData.manager) {
            return Position.manager;
        } else if (positionData==PositionData.member) {
            return Position.member;
        }
        return Position.nothing;
    }
}
