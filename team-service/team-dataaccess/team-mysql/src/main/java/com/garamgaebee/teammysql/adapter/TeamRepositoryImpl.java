package com.garamgaebee.teammysql.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.ExternalLink;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import com.garamgaebee.teammysql.entity.*;
import com.garamgaebee.teammysql.mapper.TeamDataAccessMapper;
import com.garamgaebee.teammysql.repository.*;
import com.garamgaebee.teammysql.valueobject.PositionData;
import com.garamgaebee.teammysql.valueobject.StateData;
import com.garamgaebee.teammysql.valueobject.TeamMemberStateData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
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
        List<TeamMemberEntity> teamMemberEntityList = teamMemberJPARepository.findTop2ByTeamEntityIdAndStateOrderByIdDesc(teamId.getValue(), TeamMemberStateData.ACTIVE);
        return teamMemberEntityList.stream().map(
                TeamMemberEntity::getMemberId
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveNotification(Team team) {
        Notification notification = team.getNotificationList().get(0);
        TeamEntity teamEntity = findTeamEntityByTeamId(team.getId().getValue());
        TeamNotificationEntity teamNotificationEntity = teamNotificationJPARepository.save(teamDataAccessMapper.makeToNotificationEntity(notification, teamEntity));
        teamNotificationImageJpaRepository.saveAll(teamDataAccessMapper.notificationAndNotificationEntityToNotificationImageEntity(notification, teamNotificationEntity));
    }

    @Override
    public Team findAllByTeamId(TeamId teamId) {
        TeamEntity teamEntity = findTeamEntityByTeamId(teamId.getValue());
        List<TeamExternalLinkEntity> teamExternalLinkEntityList = findTeamExternalLinkEntityByTeamId(teamId.getValue());
        List<TeamMemberEntity> teamMemberEntityList = findTeamMemberEntityByTeamId(teamId.getValue());
        List<TeamNotificationEntity> teamNotificationEntityList = findTeamNotificationEntityByTeamId(teamId.getValue());
        List<List<TeamNotificationImageEntity>> teamNotificationImageJpaRepositoryList = new ArrayList<>();
        for (TeamNotificationEntity teamNotificationEntity : teamNotificationEntityList) {
            teamNotificationImageJpaRepositoryList.add(findTeamNotificationImageEntityByTeamId(teamNotificationEntity.getId()));

        }
        return teamDataAccessMapper.teamEntityToTeamWithAll(teamEntity, teamExternalLinkEntityList, teamMemberEntityList, teamNotificationEntityList, teamNotificationImageJpaRepositoryList);
    }

    @Override
    public Position findMemberPositionInTeam(Member member) {
        TeamMemberEntity teamMemberEntity = teamMemberJPARepository.findByTeamEntityIdAndMemberIdAndState(member.getTeam().getId().getValue(), member.getId().getValue(), TeamMemberStateData.ACTIVE).orElseThrow(() -> new BaseException(BaseErrorCode.NOT_FOUND_TEAM_MEMBER));
        return teamDataAccessMapper.positionDataToPosition(teamMemberEntity.getPosition());
    }

    @Override
    @Transactional
    public void doneTeam(Member member) {
        TeamEntity teamEntity = findTeamEntityByTeamId(member.getTeam().getId().getValue());
        teamEntity.doneTeam();
    }

    @Transactional
    @Override
    public void exitTeam(Member member) {
        TeamMemberEntity teamMemberEntity = teamMemberJPARepository.findByTeamEntityIdAndMemberIdAndState(member.getTeam().getId().getValue(), member.getId().getValue(), TeamMemberStateData.ACTIVE).orElseThrow(() -> new BaseException(BaseErrorCode.NOT_FOUND_TEAM_MEMBER));
        teamMemberEntity.exitTeam();
    }

    @Transactional
    @Override
    public void editTeam(Team team) {
        TeamEntity teamEntity = findTeamEntityByTeamId(team.getId().getValue());
        teamEntity.setTeamIntroduce(team.getIntroduce().getContent());
        teamEntity.setTeamName(team.getTeamName());
        deleteTeamExternalLink(team.getId().getValue());
        teamExternalLinkJpaRepository.saveAll(
                team.getExternalLink().stream().map(
                        externalLink -> TeamExternalLinkEntity.builder()
                                .id(externalLink.getExternalLinkLinkId())
                                .teamEntity(teamEntity)
                                .memberId(team.getTeamMember().get(0).getId().getValue())
                                .link(externalLink.getLink()).build()
                ).collect(Collectors.toList())
        );
    }

    @Override
    public List<Team> findTeamListByMemberId(Member member) {
        List<TeamMemberEntity> teamMemberEntity = teamMemberJPARepository.findByMemberIdAndState(member.getId().getValue(), TeamMemberStateData.ACTIVE);
        return teamDataAccessMapper.teamMemberEntityToTeamByFindTeamListByMemberId(teamMemberEntity);
    }

    @Override
    public Position findMemberPositionByTeamId(UUID teamId, UUID memberId) {
        Optional<TeamMemberEntity> teamMemberEntityOptional = teamMemberJPARepository.findByTeamEntityIdAndMemberIdAndState(teamId, memberId, TeamMemberStateData.ACTIVE);
        return teamDataAccessMapper.teamMemberEntityToAuthority(teamMemberEntityOptional);
    }

    @Override
    public Long findMemberNums(UUID teamId) {
        return teamMemberJPARepository.countByTeamEntityIdAndState(teamId, TeamMemberStateData.ACTIVE);
    }

    @Override
    public UUID findLeaderByTeamId(UUID teamId) {
        TeamMemberEntity member = teamMemberJPARepository.findByTeamEntityIdAndPositionAndState(teamId, PositionData.leader, TeamMemberStateData.ACTIVE).orElseThrow(() -> new BaseException(BaseErrorCode.NOT_FOUND_TEAM_LEADER));
        return member.getMemberId();
    }

    @Override
    public List<Notification> findNotificationByTeamId(UUID teamId) {
        List<TeamNotificationEntity> teamNotificationEntity = teamNotificationJPARepository.findAllByTeamEntityId(teamId);
        return teamDataAccessMapper.notificationEntityListToNotificationList(teamNotificationEntity);
    }

    @Override
    public List<ExternalLink> findTeamIdByNotificationList(UUID teamId) {
        List<TeamExternalLinkEntity> teamExternalLinkEntityList = findTeamExternalLinkEntityByTeamId(teamId);
        return teamDataAccessMapper.teamExternalLinkEntityListToExternalLinkList(teamExternalLinkEntityList);
    }

    public void deleteTeamExternalLink(UUID teamId) {
        List<TeamExternalLinkEntity> teamExternalLinkEntityList = findTeamExternalLinkEntityByTeamId(teamId);
        for (TeamExternalLinkEntity teamExternalLinkEntity : teamExternalLinkEntityList) {
            teamExternalLinkEntity.updateState(StateData.DELETE);
        }
    }

    public TeamEntity findTeamEntityByTeamId(UUID teamId) {
        return teamJpaRepository.findByIdAndStateData(teamId, StateData.ACTIVE).orElseThrow(() -> new BaseException(BaseErrorCode.NOT_FOUND_TEAM));
    }

    public List<TeamExternalLinkEntity> findTeamExternalLinkEntityByTeamId(UUID teamId) {
        return teamExternalLinkJpaRepository.findAllByTeamEntityIdAndStateData(teamId, StateData.ACTIVE);
    }

    public List<TeamMemberEntity> findTeamMemberEntityByTeamId(UUID teamId) {
        return teamMemberJPARepository.findAllByTeamEntityIdAndState(teamId, TeamMemberStateData.ACTIVE);
    }

    public List<TeamNotificationEntity> findTeamNotificationEntityByTeamId(UUID teamId) {
        return teamNotificationJPARepository.findAllByTeamEntityIdAndStateData(teamId, StateData.ACTIVE);
    }

    public List<TeamNotificationImageEntity> findTeamNotificationImageEntityByTeamId(UUID notificationId) {
        return teamNotificationImageJpaRepository.findAllByTeamNotificationEntityIdAndStateData(notificationId, StateData.ACTIVE);
    }
}
