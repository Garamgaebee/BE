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

    public void changeIsPushNewFunctionEventStatus() {
        this.setIsPushNewFunctionEvent(!this.getIsPushTeamEvent());
    }

    public void changeIsPushTeamEventStatus() {

        this.setIsPushTeamEvent(!this.getIsPushTeamEvent());
    }

    public void changeIsPushThreadEventStatus() {
        this.setIsPushThreadEvent(!this.getIsPushThreadEvent());
    }

    public void changeIsPushHotThreadEventStatus() {
        this.setIsPushHotThreadEvent(!this.getIsPushHotThreadEvent());
    }
}
