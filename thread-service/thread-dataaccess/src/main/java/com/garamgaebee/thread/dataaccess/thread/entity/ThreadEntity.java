package com.garamgaebee.thread.dataaccess.thread.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "thread")
public class ThreadEntity extends ThreadBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "thread_idx", unique = true, nullable = false)
    private UUID threadIdx;

    @Column(name = "member_idx", nullable = false)
    private UUID memberIdx;

    @Column(name = "team_idx", nullable = false)
    private UUID teamIdx;

    @Column(name = "content", nullable = false, length = 501)
    private String content;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_idx")
    private List<LikeEntity> likes;


}
