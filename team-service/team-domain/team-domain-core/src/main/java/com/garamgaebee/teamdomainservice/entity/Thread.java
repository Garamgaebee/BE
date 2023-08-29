package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Thread extends AggregateRoot<TeamId> {
    Member member;
    String content;
    int likeCount;
    int commentCount;
    LocalDateTime date;

    public void setMember(Member member) {
        this.member = member;
    }
}