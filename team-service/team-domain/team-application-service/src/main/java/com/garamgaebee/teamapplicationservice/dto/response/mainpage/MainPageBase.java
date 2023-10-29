package com.garamgaebee.teamapplicationservice.dto.response.mainpage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@ToString
public class MainPageBase {
    String teamImage;
    String teamName;
    String teamCreatedAt;
    int teamSize;
    String teamIntroduce;
    String teamEndAt;
    MainPageLeaderInfo mainPageLeaderInfo;

    public MainPageBase(String image, String name, String teamCreatedAt, int teamSize, String introduction, String teamEndAt, MainPageLeaderInfo mainPageLeaderInfo) {
        this.mainPageLeaderInfo = mainPageLeaderInfo;
        this.teamCreatedAt = teamCreatedAt;
        this.teamEndAt = teamEndAt;
        this.teamName = name;
        this.teamIntroduce = introduction;
        this.teamSize = teamSize;
        this.teamImage = image;
    }

    public MainPageBase(MainPageBase mainPageBase) {
        this.mainPageLeaderInfo = mainPageBase.getMainPageLeaderInfo();
        this.teamCreatedAt = mainPageBase.getTeamCreatedAt();
        this.teamEndAt = mainPageBase.getTeamEndAt();
        this.teamName = mainPageBase.getTeamName();
        this.teamIntroduce = mainPageBase.getTeamIntroduce();
        this.teamSize = mainPageBase.getTeamSize();
        this.teamImage = mainPageBase.getTeamImage();
    }

    public void setEndedAt(String teamEndAt) {
        this.teamEndAt = teamEndAt;
    }
}