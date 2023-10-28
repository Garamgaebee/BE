package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teammysql.entity.TeamMemberEntity;
import com.garamgaebee.teammysql.valueobject.PositionData;
import com.garamgaebee.teammysql.valueobject.StateData;
import com.garamgaebee.teammysql.valueobject.TeamMemberStateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TeamMemberJPARepository extends JpaRepository<TeamMemberEntity,Long> {
    List<TeamMemberEntity> findTop2ByTeamEntityIdAndStateOrderByIdDesc(UUID teamEntity_id, TeamMemberStateData state);

    List<TeamMemberEntity> findAllByTeamEntityIdAndState(UUID value, TeamMemberStateData state);

    Optional<TeamMemberEntity> findByTeamEntityIdAndMemberIdAndState(UUID teamId, UUID memberId, TeamMemberStateData teamMemberStateData);

    List<TeamMemberEntity> findByMemberIdAndState(UUID value, TeamMemberStateData teamMemberStateData);

    Long countByTeamEntityIdAndState(UUID teamId, TeamMemberStateData stateData);

    Optional<TeamMemberEntity> findByTeamEntityIdAndPositionAndState(UUID teamId, PositionData position, TeamMemberStateData teamMemberStateData);
}
