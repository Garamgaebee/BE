package com.garamgaebee.notification.service.domain.port.output.persistence;

import java.time.LocalDateTime;
import java.util.List;

public interface DeleteFcmTokenPort {

    public void deleteFcmTokenListByTokenValue(List<String> tokenList);

    public void deleteSingleFcmTokenByTokenValue(String token);

    public void deleteFcmTokenListBeforeTime(LocalDateTime time);

}
