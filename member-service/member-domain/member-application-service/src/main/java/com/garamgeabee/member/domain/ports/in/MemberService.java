package com.garamgeabee.member.domain.ports.in;


import com.garamgeabee.member.domain.dto.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public interface MemberService {
    GetMemberResponse getMember(UUID memberIdx);

    DeleteMemberResponse deleteMember(UUID memberIdx);

    UUID createMember(CreateMemberCommand req);

    ProfileImgResponse postProfileImg(String memberIdx, MultipartFile file);

    ProfileImgResponse patchProfileImg(String memberIdx, MultipartFile file);

    boolean checkDuplicateNickname(String nickname);

    GetFeignMemberResponse getFeignMember(String userIdx);
}
