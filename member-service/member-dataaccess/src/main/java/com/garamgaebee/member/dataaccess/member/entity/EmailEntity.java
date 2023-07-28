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
@Table(name = "member_email")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EmailEntity {

    @Id @Column(name = "email_idx")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID emailIdx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private MemberEntity memberIdx;

    @Column(name = "email", length = 45)
    private String email;

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
    public EmailEntity(UUID emailIdx, MemberEntity memberIdx, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.emailIdx = emailIdx;
        this.memberIdx = memberIdx;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
