package com.garamgaebee.member.domain.ports.out;


import com.garamgaebee.member.domain.entity.Member;

import java.util.List;
import java.util.UUID;

public interface MemberRepository {

    Member findMember(UUID memberIdx);

    Boolean deleteMember(Member member);

    Boolean patchMemberImage(Member member);

    boolean checkNicknameExist(String nickname);

    UUID persistMember(Member member);

    List<Member> findMemberList(List<UUID> memberIdxList);
}
