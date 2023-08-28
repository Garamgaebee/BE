package com.garamgaebee.teammysql.adapter;

import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import com.garamgaebee.teammysql.entity.TeamEntity;
import com.garamgaebee.teammysql.entity.TeamMemberEntity;
import com.garamgaebee.teammysql.mapper.TeamDataAccessMapper;
import com.garamgaebee.teammysql.repository.TeamJpaRepository;
import com.garamgaebee.teammysql.repository.TeamMemberJPARepository;
import com.garamgaebee.teammysql.repository.TeamNotificationJPARepository;
import com.garamgaebee.teammysql.valueobject.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class TeamRepositoryImpl implements TeamRepository {
    private final TeamJpaRepository teamJpaRepository;
    private final TeamDataAccessMapper teamDataAccessMapper;
    private final TeamMemberJPARepository teamMemberJPARepository;
    private final TeamNotificationJPARepository teamNotificationJPARepository;
    @Override
    public Optional<Team> findByTeamId(TeamId teamId) {
        log.info(teamJpaRepository.findById(teamId.getValue()).get().toString());
        return teamJpaRepository.findById(teamId.getValue())
                .map(teamDataAccessMapper::teamEntityToTeam);
    }

    @Override
    public List<UUID> findMemberIdListByTeamId(TeamId teamId) {
        List<TeamMemberEntity> teamMemberEntityList = teamMemberJPARepository.findTop2ByTeamEntityIdAndStateOrderByIdDesc(teamId.getValue(), State.ACTIVE);
        return teamMemberEntityList.stream().map(
                TeamMemberEntity::getMemberId
        ).collect(Collectors.toList());
    }

    @Override
    public void saveNotification(Notification notification) {
        teamNotificationJPARepository.save(teamDataAccessMapper.notificationTonotificationEntity(notification));
    }
}
