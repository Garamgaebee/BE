package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teammysql.entity.TeamEntity;
import com.garamgaebee.teammysql.entity.TeamNotificationImageEntity;
import com.garamgaebee.teammysql.valueobject.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamNotificationImageJpaRepository extends JpaRepository<TeamNotificationImageEntity,Long> {
    List<TeamNotificationImageEntity> findAllByTeamNotificationEntityIdAndState(UUID notificationId, State state);
}
