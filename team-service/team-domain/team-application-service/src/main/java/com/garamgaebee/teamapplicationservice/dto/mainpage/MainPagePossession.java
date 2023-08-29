package com.garamgaebee.teamapplicationservice.dto.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MainPagePossession extends MainPageBase{
    int teamMemberSize;
    int teamManagerSize;
    int teamSize;
    List<MainPageTeamMember> teamMemberList = new ArrayList<>();;
}
