package com.garamgeabee.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchMemberImgCommand {
    private String memberIdx;
    private String imgUrl;

}
