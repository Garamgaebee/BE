package com.garamgaebee.teamapplicationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetMemberTeam {
    String teamName;
    String teamImageUrl;
    String teamPosition;
}
