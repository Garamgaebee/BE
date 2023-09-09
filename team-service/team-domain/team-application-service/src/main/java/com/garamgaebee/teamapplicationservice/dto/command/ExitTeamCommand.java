package com.garamgaebee.teamapplicationservice.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ExitTeamCommand {
    UUID teamId;
    UUID memberId;
}
