package com.garamgaebee.auth.service.domain;

import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import com.garamgaebee.auth.service.domain.mapper.AuthDataMapper;
import com.garamgaebee.auth.service.domain.port.output.repository.AuthenticationRepository;
import com.garamgaebee.auth.service.domain.port.output.web.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Oauth2UserRegisterHandler {

    private final AuthenticationRepository authenticationRepository;
    private final OpenFeignClient openFeignClient;

    protected Authentication persistOauth2User(OauthUserProfile userProfile) {

        // oauthId로 사용자 조회
        Optional<Authentication> authentication =  authenticationRepository.findAuthenticationByOauthId(userProfile.getOauthId());

        // 존재하지 않는 사용자이면 생성 및 저장한 뒤 반환
        if(authentication.isEmpty()) {
            return registerMember(userProfile);
        }

        // 존재하는 사용자이면 그대로 반환
        return authentication.get();
    }

    protected Authentication registerMember(OauthUserProfile userProfile) {
        // Authentication 객체 생성
        Authentication newAuthentication = new Authentication().init(userProfile.getOauthId(), UUID.randomUUID());
        // DB에 생성된 객체 저장
        return authenticationRepository.persistAuthentication(newAuthentication);
    }

    protected void updateMember(OauthUserProfile userProfile) {
        // TODO 필요한 경우 멤버 정보(최종 로그인 시간 등) 업데이트
    }

    public Authentication findMemberByMemberId(UUID memberId) {
        //TODO 반환 에러 정의
        return authenticationRepository.findAuthenticationByMemberId(memberId).orElseThrow(() -> new RuntimeException());
    }
}
