package com.garamgaebee.auth.service.messaging.adapter;

import com.garamgaebee.auth.service.domain.dto.create.CreateMemberRequest;
import com.garamgaebee.auth.service.domain.port.output.web.OpenFeignClient;
import com.garamgaebee.auth.service.messaging.publisher.MemberFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OpenFeignClientImpl implements OpenFeignClient {

    private final MemberFeignClient memberFeignClient;
    @Override
    public UUID registerMember(CreateMemberRequest createMemberRequest) {
        return memberFeignClient.registerNewMember(createMemberRequest);
    }

    @Override
    public Boolean checkDuplicateNickname(String nickname) {
        return memberFeignClient.checkDuplicateNickname(nickname);
    }

    @Override
    public Boolean exitMember(UUID memberId) {
        //TODO 회원 탈퇴 api 연결
        return null;
    }
}
