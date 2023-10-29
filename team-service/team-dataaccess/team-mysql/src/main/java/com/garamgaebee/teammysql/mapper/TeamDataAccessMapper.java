package com.garamgaebee.teammysql.mapper;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.*;
import com.garamgaebee.teammysql.entity.*;
import com.garamgaebee.teammysql.valueobject.IsOpenedData;
import com.garamgaebee.teammysql.valueobject.PositionData;
import com.garamgaebee.teammysql.valueobject.StateData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TeamDataAccessMapper {
    public Team teamEntityToTeam(TeamEntity teamEntity) {
        return Team.builder()
                .teamName(teamEntity.getName())
                .teamId(new TeamId(teamEntity.getId()))
                .isOpened(IsOpenedDataToIsOpened(teamEntity.getIsOpenedData()))
                .introduce(new Introduce(teamEntity.getIntroduction()))
                .state(TeamStateDataToTeamState(teamEntity.getStateData()))
                .image(new Image(teamEntity.getImage()))
                .endedAt(teamEntity.getEndedAt())
                .createdAt(teamEntity.getCreatedAt())
                .updatedAt(teamEntity.getUpdatedAt())
                .build();
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
        return Position.guest;
    }

    private IsOpened IsOpenedDataToIsOpened(IsOpenedData isOpenedData) {
        return switch (isOpenedData) {
            case PUBLIC -> IsOpened.PUBLIC;
            case PRIVATE -> IsOpened.PRIVATE;
        };
    }

    private State TeamStateDataToTeamState(StateData stateData) {
        return switch (stateData) {
            case ACTIVE -> State.ACTIVE;
            case DELETE -> State.DELETE;
            case DONE -> State.DONE;
        };
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

    public Position teamMemberEntityToAuthority(Optional<TeamMemberEntity> teamMemberEntityOptional) {
        return teamMemberEntityOptional.map(teamMemberEntity -> switch (teamMemberEntity.getPosition()) {
            case leader -> Position.leader;
            case manager -> Position.manager;
            case member -> Position.member;
        }).orElse(Position.guest);
    }

    public Member teamMemberEntityToTeamMember(TeamMemberEntity member) {
        return Member.builder()
                .position(Position.leader)
                .memberId(new MemberId(member.getMemberId()))
                .build();
    }

    public List<Notification> notificationEntityListToNotificationList(List<TeamNotificationEntity> teamNotificationEntity) {
        if(teamNotificationEntity.isEmpty())
            return new ArrayList<>();
        return teamNotificationEntity.stream().map(
                t ->
                        Notification.builder()
                                .content(t.getContent())
                                .createdAt(t.getCreatedAt())
                                .updatedAt(t.getUpdatedAt())
                                .build()
        ).collect(Collectors.toList());
    }

    public List<ExternalLink> teamExternalLinkEntityListToExternalLinkList(List<TeamExternalLinkEntity> teamExternalLinkEntityList) {
        if (teamExternalLinkEntityList.isEmpty())
            return new ArrayList<>();
        return teamExternalLinkEntityList.stream().map(
                teamExternalLinkEntity -> ExternalLink.builder()
                        .externalLinkLinkId(teamExternalLinkEntity.getId())
                        .link(teamExternalLinkEntity.getLink()).build()
        ).collect(Collectors.toList());
    }
}
