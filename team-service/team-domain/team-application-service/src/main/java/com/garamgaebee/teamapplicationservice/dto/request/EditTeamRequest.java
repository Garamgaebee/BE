package com.garamgaebee.teamapplicationservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EditTeamRequest {
    private String communityName;
    private String communityIntro;
    private String communityLink;
}
