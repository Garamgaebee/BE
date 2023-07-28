package com.garamgeabee.member.domain.entity;

import com.garamgeabee.member.domain.valueobject.MemberId;

public class Member extends AggregateRoot<MemberId>{
    public Member() {
    }

    public Member(MemberId memberId){ super.setId(memberId);}
}
