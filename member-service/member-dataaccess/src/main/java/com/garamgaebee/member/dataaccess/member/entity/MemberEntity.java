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
import java.util.List;
import java.util.UUID;

@Entity @Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID memberIdx;

    @Column(name = "member_name")
    private String memberName;
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "company")
    private String company;

    @Column(name = "duty")
    private String duty;

    @Column(name = "level")
    private String level;

    @Lob
    @Column(name = "profile_img_url", columnDefinition = "TEXT")
    private String profileImgUrl;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private Boolean status;

    @OneToMany(mappedBy = "memberIdx")
    private List<CareerEntity> careers;

    @OneToMany(mappedBy = "memberIdx")
    private List<EmailEntity> emails;

    @OneToMany(mappedBy = "memberIdx")
    private List<SnsEntity> snses;

    @Builder
    public MemberEntity(UUID memberIdx, String memberName, String nickname, String company, String duty, String level, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean status) {
        this.memberIdx = memberIdx;
        this.memberName = memberName;
        this.nickname = nickname;
        this.company = company;
        this.duty = duty;
        this.level = level;
        this.profileImgUrl = profileImgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public void deleteMember(boolean status){
        this.status = status;
    }

    public void changeImageUrl(String profileImgUrl){
        this.profileImgUrl = profileImgUrl;
    }
}
