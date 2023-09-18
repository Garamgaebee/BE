package com.garamgaebee.teammysql.entity;

import com.garamgaebee.teammysql.common.BaseEntity;
import com.garamgaebee.teammysql.valueobject.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
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

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.ACTIVE;

    public void doneTeam() {
        this.state = State.DONE;
    }

    public void setTeamName(String teamName) {
        this.name = teamName;
    }

    public void setTeamIntroduce(String introduction) {
        this.introduction = introduction;
    }

}
