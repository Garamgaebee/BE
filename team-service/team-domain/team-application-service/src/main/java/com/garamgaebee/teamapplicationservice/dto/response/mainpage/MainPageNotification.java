package com.garamgaebee.teamapplicationservice.dto.response.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MainPageNotification {
    int size;
    String content;
    String createdAt;
}
