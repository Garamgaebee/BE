package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teammysql.entity.TeamNotificationImageEntity;
import com.garamgaebee.teammysql.valueobject.StateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamNotificationImageJpaRepository extends JpaRepository<TeamNotificationImageEntity,Long> {
    List<TeamNotificationImageEntity> findAllByTeamNotificationEntityIdAndStateData(UUID notificationId, StateData stateData);
}
