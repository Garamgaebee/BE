package com.garamgaebee.imageapplicationservice.ports.output;

import com.garamgaebee.imagedomaincore.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Repository {
    String save(MultipartFile imageFile, Image image);

    List<Boolean> listDelete(List<Image> urlList);
}
