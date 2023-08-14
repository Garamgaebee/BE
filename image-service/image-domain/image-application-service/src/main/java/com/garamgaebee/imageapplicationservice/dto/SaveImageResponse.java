package com.garamgaebee.imageapplicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SaveImageResponse {
    List<String> url;
}
