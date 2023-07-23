package com.garamgaebee.memberserivce.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteMemberCommand {
    private Long memberIdx;
    private String content;
    private String category;
}
