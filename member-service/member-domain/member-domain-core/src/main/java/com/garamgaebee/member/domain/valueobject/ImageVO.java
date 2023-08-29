package com.garamgaebee.member.domain.valueobject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageVO {
    List<String> url;

    public ImageVO(List<String> url) {
        this.url = url;
    }
}
