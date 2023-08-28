package com.garamgaebee.imagedomaincore.entity;

import com.garamgaebee.imagedomaincore.common.entity.AggregateRoot;
import com.garamgaebee.imagedomaincore.valueobject.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Slf4j
@ToString
public class Image extends AggregateRoot<ImageUrl> {
    private String contentType;
    private String originUrl;
    public void initializeImage() {
        setId(new ImageUrl(UUID.randomUUID() + "." + Objects.requireNonNull(contentType).split("/")[1]));
    }

    public void deleteImageInitialize() {
        setId(new ImageUrl(originUrl.split("/")[4]));
    }
}