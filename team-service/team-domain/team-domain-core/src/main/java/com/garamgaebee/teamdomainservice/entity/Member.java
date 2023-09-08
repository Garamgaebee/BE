package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.Department;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.MemberId;
import com.garamgaebee.teamdomainservice.valueobject.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
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
        return this.position==Position.leader;
    }
}