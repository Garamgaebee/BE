package com.garamgaebee.thread.dataaccess.thread.entity;

import com.garamgaebee.thread.domain.entity.ThreadStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "thread_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeEntity extends ThreadBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "like_idx", unique = true, nullable = false)
    private UUID likeIdx;

    @Column(name = "target_thread_idx", nullable = false)
    private UUID targetThreadIdx;

    @Column(name = "member_idx", nullable = false)
    private UUID memberIdx;

    @Builder
    public LikeEntity(LocalDateTime createdAt, LocalDateTime updatedAt, ThreadStatus status, UUID likeIdx, UUID targetThreadIdx, UUID memberIdx) {
        super(createdAt, updatedAt, status);
        this.likeIdx = likeIdx;
        this.targetThreadIdx = targetThreadIdx;
        this.memberIdx = memberIdx;
    }
}
