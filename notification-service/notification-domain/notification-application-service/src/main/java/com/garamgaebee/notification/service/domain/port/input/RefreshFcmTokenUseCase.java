package com.garamgaebee.notification.service.domain.port.input;

import com.garamgaebee.notification.service.domain.port.input.command.RefreshFcmTokenCommand;

public interface RefreshFcmTokenUseCase {
    public Boolean refreshFcmTokenUseCase(RefreshFcmTokenCommand refreshFcmTokenCommand);
}
