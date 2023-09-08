package com.garamgaebee.teamapplicationservice.dto.response.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MainPageThread {
    MainPageThreadMember mainPageThreadMember;
    String threadContent;
    int likeCount;
    int commentCount;
    String threadTime;
}
