package com.garamgeabee.member.domain.ports.in;


import com.garamgeabee.member.domain.dto.DeleteMemberResponse;
import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.dto.PatchMemberImgCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface MemberService {
    GetMemberResponse getMember(UUID memberIdx);

    DeleteMemberResponse deleteMember(UUID memberIdx);

    boolean insertMemberImage(PatchMemberImgCommand req);

    UUID createMember(UUID memberIdx);
}
