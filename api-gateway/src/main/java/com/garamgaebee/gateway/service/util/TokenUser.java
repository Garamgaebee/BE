package com.garamgaebee.gateway.service.util;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TokenUser {
    private String id;
    private List<String> role;
}
