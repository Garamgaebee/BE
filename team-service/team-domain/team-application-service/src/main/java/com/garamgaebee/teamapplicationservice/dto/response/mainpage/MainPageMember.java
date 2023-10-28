package com.garamgaebee.teamapplicationservice.dto.response.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class MainPageMember extends MainPageBase {
    MainPageNotification mainPageNotification;
    String referenceLink;
}
