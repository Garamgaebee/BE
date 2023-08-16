package com.garamgaebee.thread.dataaccess.thread.entity;

import com.garamgaebee.thread.domain.entity.ThreadStatus;
import com.garamgaebee.thread.domain.entity.ThreadType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "thread")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThreadEntity extends ThreadBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "thread_idx", unique = true, nullable = false)
    private UUID threadIdx;

    @Column(name = "member_idx", nullable = false)
    private UUID memberIdx;

    @Column(name = "thread_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ThreadType threadType;

    @Column(name = "team_idx")
    private String teamIdx;

    @Column(name = "content", nullable = false, length = 501)
    private String content;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "like_idx")
    private List<LikeEntity> likes;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "image_idx")
    private List<ThreadImageEntity> images;

    @Column(name = "is_comment", nullable = false)
    @ColumnDefault("false")
    private boolean isComment;

    @Column(name = "parent_idx")
    private String parentIdx;

    @Builder
    public ThreadEntity(LocalDateTime createdAt, LocalDateTime updatedAt, ThreadStatus status, UUID threadIdx, UUID memberIdx, ThreadType threadType, String teamIdx, String content, List<LikeEntity> likes, boolean isComment, String parentIdx) {
        super(createdAt, updatedAt, status);
        this.threadIdx = threadIdx;
        this.memberIdx = memberIdx;
        this.threadType = threadType;
        this.teamIdx = teamIdx;
        this.content = content;
        this.likes = likes;
        this.isComment = isComment;
        this.parentIdx = parentIdx;
    }
}
