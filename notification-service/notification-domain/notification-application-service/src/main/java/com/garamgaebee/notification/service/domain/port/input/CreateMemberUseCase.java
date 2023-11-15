package com.garamgaebee.notification.service.domain.port.input;

import com.garamgaebee.notification.service.domain.port.input.command.RegisterMemberCommand;

public interface CreateMemberUseCase {

    // 멤버 최초 등록
    public Boolean createMember(RegisterMemberCommand registerMemberCommand);

}
