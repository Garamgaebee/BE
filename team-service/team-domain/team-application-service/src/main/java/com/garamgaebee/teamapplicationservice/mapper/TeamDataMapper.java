package com.garamgaebee.teamapplicationservice.mapper;

import com.garamgaebee.teamapplicationservice.dto.command.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.command.EditTeamInfoCommand;
import com.garamgaebee.teamapplicationservice.dto.response.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.dto.response.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;
import com.garamgaebee.teamapplicationservice.dto.response.GetMemberTeam;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.*;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.Authority;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TeamDataMapper {
    public GetMainPageResponse teamToTeamMainPage(Team team) {
        GetMainPageResponse getMainPageResponse = null;
//        if (team.getAuthority().equals(Authority.DONE)) {
//            getMainPageResponse = GetMainPageResponse.builder()
//                    .authority(Authority.DONE)
//                    .data(null)
//                    .build();
//        } else if (team.getAuthority().equals(Authority.POSSESSION)) {
//        getMainPageResponse = GetMainPageResponse.builder()
//                .authority(Authority.POSSESSION)
//                .data(
//                        MainPagePossession.builder()
//                                .teamImage(team.getImage().getUrl())
//                                .teamIntroduce(team.getIntroduce().getContent())
//                                .teamName(team.getTeamName())
//                                .mainPageThreadList(
//                                        team.getTeamThread().stream().map(
//                                                thread -> MainPageThread.builder()
//                                                        .mainPageThreadMember(
//                                                                MainPageThreadMember.builder()
//                                                                        .memberTeamPosition(thread.getMember().getPosition().name())
//                                                                        .memberGroupName(thread.getMember().getTeam().getTeamName())
//                                                                        .memberName(thread.getMember().getName())
//                                                                        .memberImage(thread.getMember().getImage().getUrl())
//                                                                        .memberDivision(thread.getMember().getDepartment().getName())
//                                                                        .groupImage(thread.getMember().getTeam().getImage().getUrl())
//                                                                        .build()
//                                                        )
//                                                        .commentCount(thread.getCommentCount())
//                                                        .likeCount(thread.getLikeCount())
//                                                        .threadContent(thread.getContent())
//                                                        .threadTime(thread.getDate().toString())
//                                                        .build()
//                                        ).collect(Collectors.toList())
//                                )
//                                .threadSize(team.getSize().getThreadSize())
//                                .teamMemberList(
//                                        team.getTeamMember().stream().map(
//                                                member -> MainPageTeamMember.builder()
//                                                        .teamImage(member.getImage().getUrl())
//                                                        .memberTeamPosition(member.getPosition().name())
//                                                        .memberTeamName(member.getTeam().getTeamName())
//                                                        .memberDepartment(member.getDepartment().getName())
//                                                        .memberName(member.getName())
//                                                        .build()
//                                        ).collect(Collectors.toList())
//                                )
//                                .teamMemberSize(team.getSize().getMemberSize())
//                                .teamManagerSize(team.getSize().getManagerSize())
//                                .teamSize(team.getSize().getTeamSize())
//
//                                .build()
//                )
//                .build();
//        } else if (team.getAuthority().equals(Authority.CLOSE)) {
//            getMainPageResponse = GetMainPageResponse.builder()
//                    .authority(Authority.CLOSE)
//                    .data(null)
//                    .build();
//        } else if (team.getAuthority().equals(Authority.OPEN)) {
//            getMainPageResponse = GetMainPageResponse.builder()
//                    .authority(Authority.OPEN)
//                    .data(null)
//                    .build();
//        }

        return getMainPageResponse;
    }

    public GetFeignTeamResponse teamToFeignTeamResponse(Team team) {
        return GetFeignTeamResponse.builder()
                .image(team.getTeamName())
                .externalLink(team.getExternalLink().stream().map(
                        ExternalLink::getLink
                ).collect(Collectors.toList()))
                .introduce(team.getIntroduce().getContent())
                .memberList(team.getTeamMember().stream().map(
                        member -> GetFeignTeamResponse.Member.builder()
                                .memberId(member.getId().getValue())
                                .position(member.getPosition().name())
                                .build()
                ).collect(Collectors.toList()))
                .notificationList(
                        team.getNotificationList().stream().map(
                                notification -> GetFeignTeamResponse.Notification.builder()
                                        .content(notification.getContent())
                                        .imageUrl(notification.getImageList().stream().map(
                                                Image::getUrl
                                        ).collect(Collectors.toList())).build()
                        ).collect(Collectors.toList())
                )
                .name(team.getTeamName()).build();
    }

    public Notification createNotificationCommandToNotification(CreateNotificationCommand createNotificationCommand) {
        return Notification.builder()
                .content(createNotificationCommand.getContent())
                .imageCount(createNotificationCommand.getImageList().size())
                .build();
    }

    public CreateNotificationResponse teamToCreateNotificationResponse(Team team) {
        return CreateNotificationResponse.builder()
                .notificationId(team.getNotificationList().get(0).getId().getValue())
                .teamId(team.getId().getValue())
                .isCreate(true).build();
    }

    public Team editTeamInfoCommandToTeam(EditTeamInfoCommand editTeamInfoCommand) {
        return Team.builder()
                .teamId(new TeamId(editTeamInfoCommand.getTeamId()))
                .member(new Member(new MemberId(editTeamInfoCommand.getMemberId())))
                .introduce(new Introduce(editTeamInfoCommand.getEditTeamRequest().getCommunityIntro()))
                .externalLink(new ExternalLink(UUID.randomUUID(),editTeamInfoCommand.getEditTeamRequest().getCommunityLink()))
                .teamName(editTeamInfoCommand.getEditTeamRequest().getCommunityName())
                .build();
    }

    public List<GetMemberTeam> teamListToGetMemberTeamList(List<Team> teamList) {
        return teamList.stream().map(
                team -> GetMemberTeam.builder()
                        .teamImageUrl(team.getImage().getUrl())
                        .teamPosition(team.getTeamMember().get(0).getPosition().toString())
                        .teamName(team.getTeamName()).build()
        ).collect(Collectors.toList());
    }
}