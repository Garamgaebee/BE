package com.garamgaebee.teamapplicationservice.mapper;

import com.garamgaebee.teamapplicationservice.dto.GetMainPageResponse;
import com.garamgaebee.teamapplicationservice.dto.mainpage.*;
import com.garamgaebee.teamdomainservice.entity.Team;
import org.springframework.stereotype.Component;

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
            getMainPageResponse = GetMainPageResponse.builder()
                    .authority(Authority.POSSESSION)
                    .data(
                            MainPagePossession.builder()
                                    .teamImage(team.getImage().getUrl())
                                    .teamIntroduce(team.getIntroduce().getContent())
                                    .teamName(team.getTeamName())
                                    .mainPageThreadList(
                                            team.getTeamThread().stream().map(
                                                    thread -> MainPageThread.builder()
                                                            .mainPageThreadMember(
                                                                    MainPageThreadMember.builder()
                                                                            .memberTeamPosition(thread.getMember().getPosition().name())
                                                                            .memberGroupName(thread.getMember().getTeam().getTeamName())
                                                                            .memberName(thread.getMember().getName())
                                                                            .memberImage(thread.getMember().getImage().getUrl())
                                                                            .memberDivision(thread.getMember().getDepartment().getName())
                                                                            .groupImage(thread.getMember().getTeam().getTeamImage())
                                                                            .build()
                                                            )
                                                            .commentCount(thread.getCommentCount())
                                                            .likeCount(thread.getLikeCount())
                                                            .threadContent(thread.getContent())
                                                            .threadTime(thread.getDate().toString())
                                                            .build()
                                            ).collect(Collectors.toList())
                                    )
                                    .threadSize(team.getSize().getThreadSize())
                                                    .teamMemberList(
                                                            team.getTeamMember().stream().map(
                                                                    member -> MainPageTeamMember.builder()
                                                                            .teamImage(member.getImage().getUrl())
                                                                            .memberTeamPosition(member.getPosition().name())
                                                                            .memberTeamName(member.getTeam().getTeamName())
                                                                            .memberDepartment(member.getDepartment().getName())
                                                                            .memberName(member.getName())
                                                                            .build()
                                                            ).collect(Collectors.toList())
                                                    )
                                                    .teamMemberSize(team.getSize().getMemberSize())
                                                    .teamManagerSize(team.getSize().getManagerSize())
                                                    .teamSize(team.getSize().getTeamSize())

                                    .build()
                    )
                    .build();
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
}