package com.garamgaebee.images3.mapper;

import com.garamgaebee.images3.entity.S3Image;
import com.garamgaebee.imagedomaincore.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class S3ImageDataMapper {
    public S3Image ImageToS3Image(MultipartFile imageFile, Image image){
        return S3Image.builder()
                .imageFile(imageFile)
                .imageUrl(image.getId().getValue())
                .contentType(image.getContentType()).build();
    }
}
