package com.garamgaebee.teamapplicationservice.dto.response.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MainPageThreadMember {
    String memberImage;
    String groupImage;
    String memberName;
    String memberDivision;
    String memberGroupName;
    String memberTeamPosition;
}
