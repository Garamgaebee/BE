package com.garamgaebee.teamapplicationservice.dto;

import com.garamgaebee.teamapplicationservice.dto.mainpage.Authority;
import com.garamgaebee.teamapplicationservice.dto.mainpage.MainPageBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetMainPageResponse {
    Authority authority;
    MainPageBase data;
}
