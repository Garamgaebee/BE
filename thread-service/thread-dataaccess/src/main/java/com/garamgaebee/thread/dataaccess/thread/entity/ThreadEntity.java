package com.garamgaebee.thread.dataaccess.thread.entity;

import com.garamgaebee.thread.domain.entity.ThreadStatus;
import com.garamgaebee.thread.domain.entity.ThreadType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "thread")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThreadEntity extends ThreadBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "thread_idx", unique = true)
    private UUID threadIdx;

    @Column(name = "author_idx", nullable = false)
    private UUID authorIdx;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "thread_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ThreadType threadType;

    @Column(name = "team_idx", nullable = false)
    private Long teamIdx;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "content", nullable = false, length = 501)
    private String content;

    @Column(name = "author_img", nullable = false, columnDefinition = "TEXT")
    private String authorImageUrl;

    @Column(name = "team_img", columnDefinition = "TEXT")
    private String teamImageUrl;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "target_thread_idx")
    private List<LikeEntity> likes;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "thread_idx")
    private List<ThreadImageEntity> images;

    @Column(name = "is_comment", nullable = false)
    private boolean isComment;

    @Column(name = "parent_idx", nullable = false)
    private String parentIdx;

    @Builder
    public ThreadEntity(LocalDateTime createdAt, LocalDateTime updatedAt, ThreadStatus status, UUID threadIdx, UUID authorIdx, String authorName, ThreadType threadType, Long teamIdx, String teamName, String content, String authorImageUrl, String teamImageUrl, List<LikeEntity> likes, List<ThreadImageEntity> images, boolean isComment, String parentIdx) {
        super(createdAt, updatedAt, status);
        this.threadIdx = threadIdx;
        this.authorIdx = authorIdx;
        this.authorName = authorName;
        this.threadType = threadType;
        this.teamIdx = teamIdx;
        this.teamName = teamName;
        this.content = content;
        this.authorImageUrl = authorImageUrl;
        this.teamImageUrl = teamImageUrl;
        this.likes = likes;
        this.images = images;
        this.isComment = isComment;
        this.parentIdx = parentIdx;
    }

    public void insertImages(List<ThreadImageEntity> list){
        this.images = list;
    }

    public void insertLike(List<LikeEntity> likes){
        this.likes = likes;
    }
}
