package com.garamgaebee.teamapplicationservice.dto.command;

import com.garamgaebee.teamapplicationservice.dto.request.EditTeamRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
@Builder
@AllArgsConstructor
public class EditTeamInfoCommand {
    private UUID teamId;
    private UUID memberId;
    private EditTeamRequest editTeamRequest;
}
