package com.garamgaebee.teammysql.entity;

import com.garamgaebee.teammysql.common.BaseEntity;
import com.garamgaebee.teammysql.valueobject.StateData;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "team_notification_image")
public class TeamNotificationImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StateData stateData = StateData.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_notification_id")
    private TeamNotificationEntity teamNotificationEntity;

    @Column(name = "url")
    private String url;

}
