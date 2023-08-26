package com.garamgaebee.thread.domain.ports.out;

import com.garamgaebee.thread.domain.dto.DeleteImageCommand;
import com.garamgaebee.thread.domain.valueobject.ImageDeleteVO;
import com.garamgaebee.thread.domain.valueobject.ImageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "image-service")
public interface ImageFeignPublisher {
    /**
     * Feign Image 등록
     * */
    @RequestMapping(method = RequestMethod.POST, value = "/api/feign/images", consumes = "multipart/form-data")
    ImageVO getFeignImageUrls(@RequestPart(value = "image") List<MultipartFile> images);

    /**
     * Feign Image 삭제
     * */
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/feign/images", consumes = "application/json")
    ImageDeleteVO deleteFeignImages(@RequestBody DeleteImageCommand imageRequest);
}
