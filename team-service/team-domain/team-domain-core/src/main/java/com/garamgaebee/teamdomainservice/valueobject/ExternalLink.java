package com.garamgaebee.teamdomainservice.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalLink {
    UUID externalLinkLinkId;
    String link;
}