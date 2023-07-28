package com.garamgeabee.member.domain.valueobject;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class MemberCareer {

    private String careername;
    private boolean progress;
    private LocalDate since;
    @Nullable
    private LocalDate end;
}
