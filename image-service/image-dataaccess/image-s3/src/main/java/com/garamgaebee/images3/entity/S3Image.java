package com.garamgaebee.images3.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Getter
@Builder
public class S3Image {
    private final MultipartFile imageFile;
    private final String imageUrl;
    private final String contentType;
}
