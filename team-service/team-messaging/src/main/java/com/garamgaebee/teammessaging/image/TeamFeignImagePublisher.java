package com.garamgaebee.teammessaging.image;

import com.garamgaebee.teammessaging.image.dto.SaveImageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("image-service")
public interface TeamFeignImagePublisher {
    @RequestMapping(method = RequestMethod.POST,value = "/api/feign/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SaveImageResponse createImage(@RequestPart(value = "image") List<MultipartFile> images);
}
