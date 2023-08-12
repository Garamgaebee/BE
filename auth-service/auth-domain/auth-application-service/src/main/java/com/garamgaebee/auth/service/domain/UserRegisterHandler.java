package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.login.CommonAuthenticationPostCommand;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.entity.CommonAuthentication;
import com.garamgaebee.auth.service.domain.entity.Oauth2Authentication;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.auth.service.domain.port.output.web.OpenFeignClient;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRegisterHandler {

    private final AuthenticationRepository authenticationRepository;
    private final OpenFeignClient openFeignClient;

    public Boolean checkNicknameExist(String nickname) {

        //TODO openFeign 닉네임 중복검사 API call

        return false;
    }

    public CommonAuthentication registerCommonAuthentication(CommonAuthenticationPostCommand commonAuthenticationPostCommand) {
        // Authentication 객체 생성
        CommonAuthentication commonAuthentication = new CommonAuthentication();
        commonAuthentication.init(commonAuthenticationPostCommand.getEmail(), commonAuthenticationPostCommand.getPassword());

        // DB에 생성된 객체 저장
        CommonAuthentication newCommonAuthentication =  authenticationRepository.persistCommonAuthentication(commonAuthentication);

        //TODO openFeign 회원 등록 API call

        return newCommonAuthentication;
    }

    public void updateMember(OauthUserProfile userProfile) {
        // TODO 필요한 경우 멤버 정보(최종 로그인 시간 등) 업데이트
    }
}
