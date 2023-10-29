package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teammysql.entity.TeamEntity;
import com.garamgaebee.teammysql.valueobject.StateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamJpaRepository extends JpaRepository<TeamEntity,UUID> {
    Optional<TeamEntity> findByIdAndStateData(UUID value, StateData stateData);

    Optional<TeamEntity> getReferenceByIdAndStateData(UUID value, StateData stateData);
}
