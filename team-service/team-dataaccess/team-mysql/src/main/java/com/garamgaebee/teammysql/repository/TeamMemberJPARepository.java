package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import com.garamgaebee.teammysql.entity.TeamMemberEntity;
import com.garamgaebee.teammysql.valueobject.State;
import com.garamgaebee.teammysql.valueobject.TeamMemberState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TeamMemberJPARepository extends JpaRepository<TeamMemberEntity,Long> {
    List<TeamMemberEntity> findTop2ByTeamEntityIdAndStateOrderByIdDesc(UUID teamEntity_id, TeamMemberState state);

    List<TeamMemberEntity> findAllByTeamEntityIdAndState(UUID value, TeamMemberState state);

    Optional<TeamMemberEntity> findByTeamEntityIdAndMemberIdAndState(UUID teamId, UUID memberId, TeamMemberState teamMemberState);

    List<TeamMemberEntity> findByMemberIdAndState(UUID value, TeamMemberState teamMemberState);
}
