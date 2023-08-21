package com.garamgaebee.thread.dataaccess.thread.entity;

import com.garamgaebee.thread.domain.entity.ThreadStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "thread_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThreadImageEntity extends ThreadBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_idx", unique = true)
    private UUID imageIdx;

    @Column(name = "thread_idx", nullable = false)
    private UUID threadIdx;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String url;

    @Builder
    public ThreadImageEntity(LocalDateTime createdAt, LocalDateTime updatedAt, ThreadStatus status, UUID imageIdx, UUID threadIdx, String url) {
        super(createdAt, updatedAt, status);
        this.imageIdx = imageIdx;
        this.threadIdx = threadIdx;
        this.url = url;
    }
}
