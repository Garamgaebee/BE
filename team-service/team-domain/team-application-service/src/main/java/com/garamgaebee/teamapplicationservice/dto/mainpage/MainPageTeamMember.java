package com.garamgaebee.teamapplicationservice.dto.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MainPageTeamMember {
    String memberImage;
    String teamImage;
    String memberName;
    String memberDepartment;
    String memberTeamName;
    String memberTeamPosition;
}
