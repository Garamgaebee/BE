package com.garamgaebee.thread.domain.valueobject;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CareerVO {
    private String careername;
    private boolean progress;
    private LocalDate since;
    @Nullable
    private LocalDate end;
}
