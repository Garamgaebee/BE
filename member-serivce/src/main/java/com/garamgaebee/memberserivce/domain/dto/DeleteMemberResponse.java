package com.garamgaebee.memberserivce.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DeleteMemberResponse {
    @JsonProperty("inactive_success")
    boolean inactiveSuccess;

    public DeleteMemberResponse(boolean inactiveSuccess) {
        this.inactiveSuccess = inactiveSuccess;
    }
}
