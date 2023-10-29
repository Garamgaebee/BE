package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends AggregateRoot<MemberId> {
    Department department;
    Position position;
    Image image;
    String name;
    Team team;

    public Member(MemberId memberId) {
        super();
        setId(memberId);
    }

    @Builder
    public Member(Department department, MemberId memberId, Position position, Image image, String name, Team team) {
        setId(memberId);
        this.position = position;
        this.team = team;
        this.department = department;
        this.image = image;
        this.name = name;
    }

    public Member(MemberId memberId, Position position) {
        super();
        setId(memberId);
        this.position = position;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean checkLeader() {
        System.out.println(position);
        return this.position == Position.leader;
    }

}