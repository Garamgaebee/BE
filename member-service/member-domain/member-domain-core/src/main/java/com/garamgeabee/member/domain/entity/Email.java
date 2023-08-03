package com.garamgeabee.member.domain.entity;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Getter
public class Email {

    private UUID emailIdx;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    @Builder
    public Email(UUID emailIdx, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.emailIdx = emailIdx;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
