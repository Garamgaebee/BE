package com.garamgaebee.teamapplicationservice.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DoneTeamCommand {
    UUID teamId;
    UUID memberId;
}
