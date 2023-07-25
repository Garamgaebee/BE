package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.create.CreateOauthMemberRequest;
import com.garamgaebee.auth.service.domain.dto.create.CreateOauthMemberResponse;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.mapper.AuthDataMapper;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.auth.service.domain.port.output.web.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Oauth2UserRegisterHandler {

    private final AuthenticationRepository authenticationRepository;
    private final OpenFeignClient openFeignClient;
    private final AuthDataMapper authDataMapper;

    protected Authentication persistOauth2User(OauthUserProfile userProfile) {

        // oauthId로 사용자 조회
        Optional<Authentication> authentication =  authenticationRepository.findAuthenticationByOauthId(userProfile.getOauthId());

        // 존재하지 않는 사용자이면 저장하고 true 반환
        if(authentication.isEmpty()) {
            return registerMember(userProfile);
        }

        return authentication.get();
    }

    protected Authentication registerMember(OauthUserProfile userProfile) {
        // openFeign으로 member service에 등록
        CreateOauthMemberResponse createOauthMemberResponse = openFeignClient.registerMemberToMemberService(authDataMapper.oauthUserProfileToCreateOauthMemberRequest(userProfile));
        // Authentication 객체 생성
        Authentication newAuthentication = new Authentication().createAuthentication(createOauthMemberResponse.getOauthId(), createOauthMemberResponse.getMemberId());
        // DB에 생성된 객체 저장
        return authenticationRepository.persistAuthentication(newAuthentication);
    }

    protected void updateMember(OauthUserProfile userProfile) {
        // TODO 필요한 경우 멤버 정보(최종 로그인 시간 등) 업데이트
    }
}
