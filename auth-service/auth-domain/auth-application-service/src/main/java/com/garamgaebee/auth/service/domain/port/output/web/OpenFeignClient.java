package com.garamgaebee.auth.service.domain.port.output.web;

import com.garamgaebee.auth.service.domain.dto.create.CreateOauthMemberRequest;
import com.garamgaebee.auth.service.domain.dto.create.CreateOauthMemberResponse;

public interface OpenFeignClient {

    public CreateOauthMemberResponse registerMemberToMemberService(CreateOauthMemberRequest createOauthMemberRequest);
}
