package com.garamgaebee.notification.service.domain;

import com.garamgaebee.notification.service.domain.port.output.persistence.DeleteFcmTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FcmTokenBatchService {

    private final DeleteFcmTokenPort deleteFcmTokenPort;

    // 토큰 신선도 기준 : 2달
    private final static long REFRESH_MONTH = 2L;

    /**
     * 신선도 떨어지는 토큰 제거
     * 매달 1일 3am에 실행
     */
    @Scheduled(cron = "0 0 3 1 * ?", zone = "Asia/Seoul")
    public void deleteOldTokens() {
        deleteFcmTokenPort.deleteFcmTokenListBeforeTime(LocalDateTime.now().minusMonths(REFRESH_MONTH));
    }
}
