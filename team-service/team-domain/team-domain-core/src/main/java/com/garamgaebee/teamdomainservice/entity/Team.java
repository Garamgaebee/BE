package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Team extends AggregateRoot<TeamId> {
    IsOpened isOpened;
    List<Member> teamMember = new ArrayList<>();
    List<Thread> teamThread = new ArrayList<>();
    List<ExternalLink> externalLink = new ArrayList<>();
    Introduce introduce;
    List<Notification> notificationList = new ArrayList<>();
    Image image;
    String teamName;
    Size size;
    State state;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime endedAt;

    public Team(TeamId teamId) {
        super();
        setId(teamId);
    }

    @Builder
    public Team(TeamId teamId, List<Member> teamMember, Member member, List<Thread> teamThread, ExternalLink externalLink,
                List<ExternalLink> externalLinkList, Introduce introduce, List<Notification> notificationList,Notification notification,
                Image image, String teamName, Size size, IsOpened isOpened, State state, LocalDateTime createdAt,
                LocalDateTime endedAt, LocalDateTime updatedAt) {
        setId(teamId);
        this.teamMember = teamMember != null ? teamMember : new ArrayList<>();
        this.teamMember.add(member);
        this.externalLink = externalLinkList != null ? externalLinkList : new ArrayList<>();
        this.externalLink.add(externalLink);
        this.teamThread = teamThread != null ? teamThread : new ArrayList<>();
        this.introduce = introduce;
        this.notificationList = notificationList != null ? notificationList : new ArrayList<>();
        this.notificationList.add(notification);
        this.teamName = teamName;
        this.size = size;
        this.image = image;
        this.isOpened = isOpened;
        this.state = state;
        this.createdAt = createdAt;
        this.endedAt = endedAt;
        this.updatedAt = updatedAt;
    }

    public void setMember(List<Member> memberList) {
        this.teamMember = memberList;
    }

    public void setThreadList(List<Thread> threadList) {
        this.teamThread = threadList;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void addNotification(Notification notification) {
        this.notificationList.add(notification);
    }

    public void validateName(String teamName) {
        if (teamName.length() > 28)
            throw new BaseException(BaseErrorCode.TEAM_OVER_TEAM_NAME_LENGTH);
    }

    public void validateIntroduce(String content) {
        if (content.length() > 80)
            throw new BaseException(BaseErrorCode.TEAM_OVER_TEAM_CONTENT_LENGTH);
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public void setExternalLine(List<ExternalLink> externalLinkList) {
        this.externalLink = externalLinkList;
    }
}