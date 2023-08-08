package com.garamgeabee.member.domain.ports.out;


import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.entity.Member;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {

    Member findMember(UUID memberIdx);

    Boolean deleteMember(Member member);

    Boolean patchMemberImage(Member member);

    boolean checkNicknameExist(String nickname);

    UUID persistMember(Member member);
}
