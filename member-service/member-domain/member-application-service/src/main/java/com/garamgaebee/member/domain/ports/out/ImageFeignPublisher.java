package com.garamgaebee.member.domain.ports.out;

import com.garamgaebee.member.domain.dto.DeleteImageCommand;
import com.garamgaebee.member.domain.valueobject.ImageDeleteVO;
import com.garamgaebee.member.domain.valueobject.ImageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "image-service")
public interface ImageFeignPublisher {
    /**
     * Feign Image 업로드
     * */
    @RequestMapping(method = RequestMethod.POST, value = "/api/feign/images", consumes = "multipart/form-data")
    ImageVO getFeignImageUrls(@RequestPart(value = "image") List<MultipartFile> images);

    /**
     * Feign Image 삭제
     * */
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/feign/images", produces = "application/json")
    ImageDeleteVO deleteFeignImages(@RequestBody DeleteImageCommand imageRequest);
}

