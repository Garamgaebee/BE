package com.garamgaebee.thread.dataaccess.thread.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "thread_like")
public class LikeEntity extends ThreadBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "like_idx", unique = true, nullable = false)
    private UUID likeIdx;

    @Column(name = "target_thread_idx", nullable = false)
    private UUID targetThreadIdx;

    @Column(name = "member_idx", nullable = false)
    private UUID memberIdx;
}
