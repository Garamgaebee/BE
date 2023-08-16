package com.garamgaebee.member.dataaccess.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Getter
@Table(name = "member_sns")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SnsEntity {

    @Id @Column(name = "sns_idx")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID snsIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private MemberEntity memberIdx;

    @Column(name = "sns")
    private String sns;

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
    public SnsEntity(UUID snsIdx, MemberEntity memberIdx, String sns, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.snsIdx = snsIdx;
        this.memberIdx = memberIdx;
        this.sns = sns;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
