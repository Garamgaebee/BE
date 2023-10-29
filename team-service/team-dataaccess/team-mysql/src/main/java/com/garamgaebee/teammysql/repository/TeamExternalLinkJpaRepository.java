package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teammysql.entity.TeamExternalLinkEntity;
import com.garamgaebee.teammysql.valueobject.StateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamExternalLinkJpaRepository extends JpaRepository<TeamExternalLinkEntity,UUID> {
    List<TeamExternalLinkEntity> findAllByTeamEntityIdAndStateData(UUID value, StateData stateData);
}