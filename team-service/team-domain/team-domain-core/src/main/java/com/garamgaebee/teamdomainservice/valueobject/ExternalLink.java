package com.garamgaebee.teamdomainservice.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalLink {
    UUID externalLinkLinkId;
    String link;
}