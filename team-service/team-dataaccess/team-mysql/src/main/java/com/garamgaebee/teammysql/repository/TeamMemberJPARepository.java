package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teammysql.entity.TeamMemberEntity;
import com.garamgaebee.teammysql.valueobject.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TeamMemberJPARepository extends JpaRepository<TeamMemberEntity,Long> {
    List<TeamMemberEntity> findTop2ByTeamEntityIdAndStateOrderByIdDesc(UUID teamId, State state);
}
