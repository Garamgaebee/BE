package com.garamgaebee.teammysql.repository;

import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import com.garamgaebee.teammysql.entity.TeamMemberEntity;
import com.garamgaebee.teammysql.valueobject.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TeamMemberJPARepository extends JpaRepository<TeamMemberEntity,Long> {
    List<TeamMemberEntity> findTop2ByTeamEntityIdAndStateOrderByIdDesc(UUID teamId, State state);

    List<TeamMemberEntity> findAllByTeamEntityIdAndState(UUID value, State state);

    Optional<TeamMemberEntity> findByTeamEntityIdAndMemberIdAndState(UUID teamId, UUID memberId, State state);
}
