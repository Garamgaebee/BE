package com.garamgeabee.member.domain.entity;


import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class Career {


    private UUID careerIdx;

    private String careerName;

    private Boolean isProgress;

    private LocalDate sinceDate;

    @Nullable
    private LocalDate endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    @Builder
    public Career(UUID careerIdx, String careerName, Boolean isProgress, LocalDate sinceDate, @Nullable LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.careerIdx = careerIdx;
        this.careerName = careerName;
        this.isProgress = isProgress;
        this.sinceDate = sinceDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
