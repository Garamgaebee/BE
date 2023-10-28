package com.garamgaebee.teammessaging.member.dto;


import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberCareer {

    private String careername;
    private boolean progress;
    private LocalDate since;
    @Nullable
    private LocalDate end;

    @Builder
    public MemberCareer(String careername, boolean progress, LocalDate since, @Nullable LocalDate end) {
        this.careername = careername;
        this.progress = progress;
        this.since = since;
        this.end = end;
    }
}
