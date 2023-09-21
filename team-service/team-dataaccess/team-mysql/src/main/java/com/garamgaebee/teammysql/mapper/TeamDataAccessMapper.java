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
        Team team = Team.builder()
                .teamName(teamEntity.getName())
                .introduce(new Introduce(teamEntity.getIntroduction()))
                .image(new Image(teamEntity.getImage()))
                .build();
        team.setId(new TeamId(teamEntity.getId()));
        return team;
    }

    public TeamNotificationEntity makeToNotificationEntity(Notification notification, TeamEntity teamEntity) {
        return TeamNotificationEntity.builder()
                .id(notification.getId().getValue())
                .teamEntity(teamEntity)
                .content(notification.getContent())
                .build();
    }

    public Team teamEntityToTeamWithAll(TeamEntity teamEntity, List<TeamExternalLinkEntity> teamExternalLinkEntityList, List<TeamMemberEntity> teamMemberEntityList, List<TeamNotificationEntity> teamNotificationEntityList, List<List<TeamNotificationImageEntity>> teamNotificationImageJpaRepositoryList) {
        List<Member> memberList = teamMemberEntityList.stream().map(
                teamMemberEntity -> new Member(new MemberId(teamMemberEntity.getMemberId()), positionToDomainPosition(teamMemberEntity.getPosition()))
        ).toList();

        List<ExternalLink> externalLinkList = teamExternalLinkEntityList.stream().map(
                teamExternalLinkEntity -> new ExternalLink(teamExternalLinkEntity.getId(), teamExternalLinkEntity.getLink())
        ).toList();

        List<Notification> notificationList = IntStream.range(0, teamNotificationEntityList.size()).boxed().map(i -> {
            List<Image> imageList = teamNotificationImageJpaRepositoryList.get(i).stream().map(
                    teamNotificationImageEntity -> new Image(teamNotificationImageEntity.getUrl())
            ).toList();
            String content = teamNotificationEntityList.get(i).getContent();
            return Notification.builder()
                    .imageList(imageList)
                    .content(content).build();
        }).toList();

        return Team.builder()
                .teamMember(memberList)
                .teamName(teamEntity.getName())
                .introduce(new Introduce(teamEntity.getIntroduction()))
                .externalLinkList(externalLinkList)
                .image(new Image(teamEntity.getImage()))
                .notificationList(notificationList)
                .build();
    }

    public Position positionToDomainPosition(PositionData positionData) {
        if (positionData == PositionData.manager) {
            return Position.manager;
        } else if (positionData == PositionData.member) {
            return Position.member;
        }
        return Position.nothing;
    }

    public List<TeamNotificationImageEntity> notificationAndNotificationEntityToNotificationImageEntity(Notification notification, TeamNotificationEntity teamNotificationEntity) {
        return notification.getImageList().stream().map(
                image -> TeamNotificationImageEntity.builder()
                        .teamNotificationEntity(teamNotificationEntity)
                        .url(image.getUrl())
                        .build()
        ).collect(Collectors.toList());
    }

    public Position positionDataToPosition(PositionData position) {
        return switch (position) {
            case member -> Position.member;
            case manager -> Position.manager;
            case leader -> Position.leader;
        };
    }

    public List<Team> teamMemberEntityToTeamByFindTeamListByMemberId(List<TeamMemberEntity> teamMemberEntityList) {
        return teamMemberEntityList.stream().map(
                teamMemberEntity -> Team.builder()
                        .member(
                                Member.builder().position(positionDataToPosition(teamMemberEntity.getPosition())).build()
                        )
                        .teamName(teamMemberEntity.getTeamEntity().getName())
                        .image(
                                Image.builder().url(teamMemberEntity.getTeamEntity().getImage()).build()
                        ).build()
        ).collect(Collectors.toList());
    }
}
