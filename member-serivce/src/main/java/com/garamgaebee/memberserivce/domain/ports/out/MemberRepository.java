package com.garamgaebee.memberserivce.domain.ports.out;

import com.garamgaebee.memberserivce.dataaccess.member.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long memberIdx);
}
