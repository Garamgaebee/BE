package com.garamgaebee.notification.service.domain.port.input.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNotificationResponse {
    private Long id;
    private Boolean event;
    private Boolean community;
    private Boolean posting;
    private Boolean hotPosting;
}
