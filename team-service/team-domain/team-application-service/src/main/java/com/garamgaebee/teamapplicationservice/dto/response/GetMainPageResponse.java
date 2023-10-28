package com.garamgaebee.teamapplicationservice.dto.response;

import com.garamgaebee.teamapplicationservice.dto.response.mainpage.MainPageLeaderInfo;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.MainPageMember;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.MainPageNotification;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.vo.AuthorityResponseVo;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.MainPageBase;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.vo.IsOpenedResponseVo;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.vo.TeamStateResponseVo;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teamdomainservice.valueobject.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.text.Format;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetMainPageResponse {
    AuthorityResponseVo authorityResponseVo;
    TeamStateResponseVo teamStateResponseVo;
    IsOpenedResponseVo isOpenedResponseVo;
    String teamImage;
    String teamName;
    String teamCreatedAt;
    int teamSize;
    String teamIntroduce;
    String teamEndAt;
    MainPageLeaderInfo mainPageLeaderInfo;
    MainPageNotification mainPageNotification;
    String referenceLink;

    public GetMainPageResponse(Team team, Position memberPosition, int teamSize, Member leader) {
        this.teamImage = team.getImage().getUrl();
        this.teamName = team.getTeamName();
        this.teamCreatedAt = team.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.teamSize = teamSize;
        this.teamIntroduce = team.getIntroduce().getContent();
        this.authorityResponseVo = switch (memberPosition) {
            case member, manager -> AuthorityResponseVo.MEMBER;
            case guest -> AuthorityResponseVo.GUEST;
            case leader -> AuthorityResponseVo.LEADER;
        };
        this.teamStateResponseVo = switch (team.getState()) {
            case ACTIVE -> TeamStateResponseVo.ACTIVE;
            case DONE, DELETE -> TeamStateResponseVo.INACTIVE;
        };
        this.isOpenedResponseVo = switch (team.getIsOpened()) {
            case PRIVATE -> IsOpenedResponseVo.PRIVATE;
            case PUBLIC -> IsOpenedResponseVo.PUBLIC;
        };
        if (team.getState().equals(State.DONE)) {
            this.teamEndAt = team.getEndedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
        if (authorityResponseVo == AuthorityResponseVo.MEMBER || authorityResponseVo == AuthorityResponseVo.LEADER) {
            this.mainPageLeaderInfo = MainPageLeaderInfo.builder()
                    .Department(leader.getDepartment().getName())
                    .name(leader.getName())
                    .url(leader.getImage().getUrl())
                    .build();
            this.referenceLink = !team.getExternalLink().isEmpty() ? team.getExternalLink().get(0).getLink() : "";
        }
    }
}