package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.create.CommonAuthenticationPostCommand;
import com.garamgaebee.auth.service.domain.dto.create.CreateMemberRequest;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.auth.service.domain.port.output.web.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterHandler {

    private final AuthenticationRepository authenticationRepository;
    private final OpenFeignClient openFeignClient;

    public Boolean checkNicknameExist(String nickname) {

        // openFeign 닉네임 중복검사 API call
        return openFeignClient.checkDuplicateNickname(nickname);
    }

    public CommonAuthentication registerCommonAuthentication(CommonAuthenticationPostCommand commonAuthenticationPostCommand) {
        // Authentication 객체 생성
        CommonAuthentication commonAuthentication = new CommonAuthentication();
        commonAuthentication.init(commonAuthenticationPostCommand.getEmail(), commonAuthenticationPostCommand.getPassword());

        // DB에 생성된 객체 저장
        CommonAuthentication newCommonAuthentication =  authenticationRepository.persistCommonAuthentication(commonAuthentication);

        // openFeign 회원 등록 API call
        openFeignClient.registerMember(CreateMemberRequest.builder()
                        .memberIdx(newCommonAuthentication.getMemberId())
                        .nickname(commonAuthenticationPostCommand.getNickname())
                        .department(commonAuthenticationPostCommand.getDepartment())
                        .memberType(commonAuthenticationPostCommand.getType())
                .build());

        return newCommonAuthentication;
    }
}
