package com.garamgaebee.teammysql.entity;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teammysql.common.BaseEntity;
import com.garamgaebee.teammysql.valueobject.PositionData;
import com.garamgaebee.teammysql.valueobject.State;
import com.garamgaebee.teammysql.valueobject.TeamMemberState;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "team_member")
public class TeamMemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PositionData position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamEntity teamEntity;

    @Column(name = "member_id")
    private UUID memberId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TeamMemberState state = TeamMemberState.ACTIVE;

    public void exitTeam() {
        this.state = TeamMemberState.EXIT;
    }
}
