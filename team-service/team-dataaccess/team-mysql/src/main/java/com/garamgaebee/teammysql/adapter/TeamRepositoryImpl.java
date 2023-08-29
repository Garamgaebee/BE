package com.garamgaebee.teammysql.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.NotificationId;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import com.garamgaebee.teammysql.entity.*;
import com.garamgaebee.teammysql.mapper.TeamDataAccessMapper;
import com.garamgaebee.teammysql.repository.*;
import com.garamgaebee.teammysql.valueobject.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private final TeamExternalLinkJpaRepository teamExternalLinkJpaRepository;
    private final TeamNotificationImageJpaRepository teamNotificationImageJpaRepository;
    @Override
    public Team findByTeamId(TeamId teamId) {
        TeamEntity teamEntity = findTeamEntityByTeamId(teamId.getValue());
        return teamDataAccessMapper.teamEntityToTeam(teamEntity);
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

    @Override
    public Team findAllByTeamId(TeamId teamId) {
        TeamEntity teamEntity = findTeamEntityByTeamId(teamId.getValue());
        List<TeamExternalLinkEntity> teamExternalLinkEntityList =  findTeamExternalLinkEntityByTeamId(teamId.getValue());
        List<TeamMemberEntity> teamMemberEntityList = findTeamMemberEntityByTeamId(teamId.getValue());
        List<TeamNotificationEntity> teamNotificationEntityList = findTeamNotificationEntityByTeamId(teamId.getValue());
        List<List<TeamNotificationImageEntity>> teamNotificationImageJpaRepositoryList = new ArrayList<>();
        for (TeamNotificationEntity teamNotificationEntity : teamNotificationEntityList) {
            teamNotificationImageJpaRepositoryList.add(findTeamNotificationImageEntityByTeamId(teamNotificationEntity.getId()));

        }
        return teamDataAccessMapper.teamEntityToTeamWithAll(teamEntity,teamExternalLinkEntityList,teamMemberEntityList,teamNotificationEntityList,teamNotificationImageJpaRepositoryList);
    }
    public TeamEntity findTeamEntityByTeamId(UUID teamId){
        return teamJpaRepository.findByIdAndState(teamId, State.ACTIVE).orElseThrow(() -> new BaseException(BaseErrorCode.NOT_FOUND_TEAM));
    }
    public List<TeamExternalLinkEntity> findTeamExternalLinkEntityByTeamId(UUID teamId){
        return teamExternalLinkJpaRepository.findAllByTeamEntityIdAndState(teamId,State.ACTIVE);
    }
    public List<TeamMemberEntity> findTeamMemberEntityByTeamId(UUID teamId){
        return teamMemberJPARepository.findAllByTeamEntityIdAndState(teamId,State.ACTIVE);
    }
    public List<TeamNotificationEntity> findTeamNotificationEntityByTeamId(UUID teamId){
        return teamNotificationJPARepository.findAllByTeamEntityIdAndState(teamId,State.ACTIVE);
    }
    public List<TeamNotificationImageEntity> findTeamNotificationImageEntityByTeamId(UUID notificationId){
        return teamNotificationImageJpaRepository.findAllByTeamNotificationEntityIdAndState(notificationId,State.ACTIVE);
    }
}
