package com.garamgaebee.teammysql.entity;

import com.garamgaebee.teammysql.common.BaseEntity;
import com.garamgaebee.teammysql.valueobject.IsOpenedData;
import com.garamgaebee.teammysql.valueobject.StateData;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "team")
public class TeamEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "is_opened")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private IsOpenedData isOpenedData = IsOpenedData.PUBLIC;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StateData stateData = StateData.ACTIVE;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public void doneTeam() {
        this.stateData = StateData.DONE;
    }

    public void setTeamName(String teamName) {
        this.name = teamName;
    }

    public void setTeamIntroduce(String introduction) {
        this.introduction = introduction;
    }

}