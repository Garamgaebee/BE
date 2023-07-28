package com.garamgeabee.member.domain.ports.out;


import com.garamgeabee.member.domain.dto.GetMemberResponse;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {

    Optional<GetMemberResponse> findMember(UUID memberIdx);

    Boolean deleteMember(UUID memberIdx);

    Boolean patchMemberImage(String memberIdx, String imgUrl);

    UUID createMember(UUID memberIdx);
}
