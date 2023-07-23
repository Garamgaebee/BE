package com.garamgaebee.memberserivce.dataaccess.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;

    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "belong", nullable = false)
    private String belong;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private Boolean status;

    public void deleteMember(){
        this.status = false;
    }
}
