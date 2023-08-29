package com.garamgaebee.teamapplicationservice.dto.mainpage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class MainPageBase {
    String teamImage;
    String teamName;
    String teamIntroduce;
    int threadSize;
    List<MainPageThread> mainPageThreadList;
}