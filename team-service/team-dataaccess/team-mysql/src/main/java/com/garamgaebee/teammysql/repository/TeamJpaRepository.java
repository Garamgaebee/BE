package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teammysql.entity.TeamEntity;
import com.garamgaebee.teammysql.valueobject.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamJpaRepository extends JpaRepository<TeamEntity,UUID> {
    Optional<TeamEntity> findByIdAndState(UUID value, State state);
}
