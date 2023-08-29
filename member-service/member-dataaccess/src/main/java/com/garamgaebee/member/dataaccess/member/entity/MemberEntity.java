package com.garamgaebee.member.dataaccess.member.entity;

import com.garamgaebee.member.domain.valueobject.MemberStatus;
import com.garamgaebee.member.domain.valueobject.MemberType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;

    @Column(name = "dept")
    private String dept;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MemberType type;

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
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "memberIdx", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CareerEntity> careers;

    @OneToMany(mappedBy = "memberIdx", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EmailEntity> emails;

    @OneToMany(mappedBy = "memberIdx", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SnsEntity> snses;

    @Builder
    public MemberEntity(UUID memberIdx, String nickname, String dept, MemberType type, String company, String duty, String level, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, MemberStatus status, List<CareerEntity> careers, List<EmailEntity> emails, List<SnsEntity> snses) {
        this.memberIdx = memberIdx;
        this.nickname = nickname;
        this.dept = dept;
        this.type = type;
        this.company = company;
        this.duty = duty;
        this.level = level;
        this.profileImgUrl = profileImgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.careers = careers;
        this.emails = emails;
        this.snses = snses;
    }

    public void deleteMember(MemberStatus status){
        this.status = status;
    }

    public void changeImageUrl(String profileImgUrl){
        this.profileImgUrl = profileImgUrl;
    }
}
