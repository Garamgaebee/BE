package com.garamgeabee.member.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class Sns {


    private UUID snsIdx;

    private String sns;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    @Builder
    public Sns(UUID snsIdx, String sns, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.snsIdx = snsIdx;
        this.sns = sns;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
