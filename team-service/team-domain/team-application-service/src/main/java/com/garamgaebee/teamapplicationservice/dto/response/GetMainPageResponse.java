package com.garamgaebee.teamapplicationservice.dto.response;

import com.garamgaebee.teamapplicationservice.dto.response.mainpage.Authority;
import com.garamgaebee.teamapplicationservice.dto.response.mainpage.MainPageBase;
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
