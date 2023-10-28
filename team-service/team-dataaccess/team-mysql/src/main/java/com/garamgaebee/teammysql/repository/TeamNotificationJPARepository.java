package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teammysql.entity.TeamNotificationEntity;
import com.garamgaebee.teammysql.valueobject.StateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TeamNotificationJPARepository extends JpaRepository<TeamNotificationEntity,UUID> {
    List<TeamNotificationEntity> findAllByTeamEntityIdAndStateData(UUID value, StateData stateData);

    List<TeamNotificationEntity> findAllByTeamEntityId(UUID teamId);
}
