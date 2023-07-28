package com.garamgeabee.member.domain.dto;


import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DeleteMemberResponse {

    boolean inactiveSuccess;

    public DeleteMemberResponse(boolean inactiveSuccess) {
        this.inactiveSuccess = inactiveSuccess;
    }
}
