package com.garamgaebee.auth.service.domain.mapper;

import com.garamgaebee.auth.service.domain.dto.create.CreateOauthMemberRequest;
import com.garamgaebee.auth.service.domain.dto.oauth.OauthUserProfile;
import com.garamgaebee.auth.service.domain.entity.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthDataMapper {

    public CreateOauthMemberRequest oauthUserProfileToCreateOauthMemberRequest(OauthUserProfile oauthUserProfile) {
        return CreateOauthMemberRequest.builder()
                .oauthId(oauthUserProfile.getOauthId())
                .name(oauthUserProfile.getName())
                .build();
    }


}
