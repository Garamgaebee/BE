package com.garamgaebee.imageapplication.rest;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.imageapplicationservice.ImageDeleteCommandHandler;
import com.garamgaebee.imageapplicationservice.dto.*;
import com.garamgaebee.imageapplicationservice.ports.input.ImageApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/feign/images")
public class ImageController {
    private final ImageApplicationService imageApplicationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SaveImageResponse createImage(@RequestPart(value = "image") List<MultipartFile> images) {
        if(images.isEmpty()) {
            log.error("Image File request is empty");
            throw new BaseException(BaseErrorCode.EMPTY_IMAGES);
        }
        SaveImageResponse saveImageResponse = imageApplicationService.saveImage(new SaveImageCommand(images));
        return saveImageResponse;
    }

    @DeleteMapping("")
    public DeleteImageResponse deleteImage(@RequestBody DeleteImageRequest imageRequest) {
        if(imageRequest.getUrlList().isEmpty()) {
            log.error("url request is empty");
            throw new BaseException(BaseErrorCode.EMPTY_IMAGES_URL);
        }
        DeleteImageResponse deleteImageResponse = imageApplicationService.deleteImage(new DeleteImageCommand(imageRequest.getUrlList()));
        return deleteImageResponse;
    }

}