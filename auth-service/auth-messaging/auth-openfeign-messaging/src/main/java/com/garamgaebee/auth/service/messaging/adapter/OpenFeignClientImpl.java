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
        if(
            //TODO 회원 탈퇴 관련 feign api 연결
                memberFeignClient.deleteMember(memberId).isInactiveSuccess()
        ) {
            return false;
        }

        return true;
    }
}
