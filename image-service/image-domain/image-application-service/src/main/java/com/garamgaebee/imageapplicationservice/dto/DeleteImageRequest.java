package com.garamgaebee.imageapplicationservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeleteImageRequest {
    List<String> urlList = new ArrayList<>();
}
