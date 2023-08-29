package com.garamgaebee.teamapplicationservice.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetMainPageMemberFeign {
    List<UUID> meberIdList = new ArrayList<>();
}
