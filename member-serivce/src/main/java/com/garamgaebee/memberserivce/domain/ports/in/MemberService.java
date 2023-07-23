package com.garamgaebee.memberserivce.domain.ports.in;

import com.garamgaebee.memberserivce.domain.dto.DeleteMemberCommand;
import com.garamgaebee.memberserivce.domain.dto.DeleteMemberResponse;
import com.garamgaebee.memberserivce.domain.dto.GetMemberResponse;
import org.springframework.stereotype.Service;

public interface MemberService {
    GetMemberResponse getMember(Long memberIdx);

    DeleteMemberResponse deleteMember(Long memberIdx);
}
