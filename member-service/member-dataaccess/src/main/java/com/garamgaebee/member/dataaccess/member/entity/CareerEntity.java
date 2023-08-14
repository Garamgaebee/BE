package com.garamgaebee.member.dataaccess.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Getter
@Table(name = "member_career")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CareerEntity {

    @Id @Column(name = "carrer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID careerIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private MemberEntity memberIdx;

    @Column(name = "carrer_name", length = 45, nullable = false)
    private String careerName;

    @Column(name = "is_progress", nullable = false)
    @ColumnDefault("false")
    private Boolean isProgress;

    @Column(name = "since_date", nullable = false)
    private LocalDate sinceDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private Boolean status;

    @Builder
    public CareerEntity(UUID careerIdx, MemberEntity memberIdx, String careerName, Boolean isProgress, LocalDate sinceDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.careerIdx = careerIdx;
        this.memberIdx = memberIdx;
        this.careerName = careerName;
        this.isProgress = isProgress;
        this.sinceDate = sinceDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
