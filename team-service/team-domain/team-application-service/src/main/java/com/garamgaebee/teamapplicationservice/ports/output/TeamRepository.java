package com.garamgaebee.teamapplicationservice.ports.output;

import com.garamgaebee.teamdomainservice.entity.Member;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.ExternalLink;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository {
    Team findByTeamId(TeamId teamId);

    List<UUID> findMemberIdListByTeamId(TeamId teamId);

    void saveNotification(Team team);

    Team findAllByTeamId(TeamId teamId);

    Position findMemberPositionInTeam(Member member);

    void doneTeam(Member member);

    void exitTeam(Member member);

    void editTeam(Team team);

    List<Team> findTeamListByMemberId(Member member);

    Position findMemberPositionByTeamId(UUID teamId,UUID MemberId);

    Long findMemberNums(UUID teamId);

    UUID findLeaderByTeamId(UUID teamId);

    List<Notification> findNotificationByTeamId(UUID teamId);

    List<ExternalLink> findTeamIdByNotificationList(UUID teamId);
}
