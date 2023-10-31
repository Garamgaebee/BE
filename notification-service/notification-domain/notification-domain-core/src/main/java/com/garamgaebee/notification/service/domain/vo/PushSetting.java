package com.garamgaebee.notification.service.domain.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PushSetting {
    private Boolean isPushNewFunctionEvent;
    private Boolean isPushTeamEvent;
    private Boolean isPushThreadEvent;
    private Boolean isPushHotThreadEvent;
}
