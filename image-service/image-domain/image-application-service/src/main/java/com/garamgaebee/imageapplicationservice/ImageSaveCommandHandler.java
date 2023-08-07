package com.garamgaebee.imageapplicationservice;

import com.garamgaebee.imageapplicationservice.dto.SaveImageCommand;
import com.garamgaebee.imageapplicationservice.dto.SaveImageResponse;
import com.garamgaebee.imageapplicationservice.mapper.ImageDataMapper;
import com.garamgaebee.imageapplicationservice.ports.output.S3Repository;
import com.garamgaebee.imagedomaincore.ImageDomainService;
import com.garamgaebee.imagedomaincore.entity.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageSaveCommandHandler {
    private final ImageDomainService imageDomainService;
    private final ImageDataMapper imageDataMapper;
    private final S3Repository s3Repository;
    public SaveImageResponse saveImage(SaveImageCommand saveImageCommand) {
        List<String> url = new ArrayList<>(saveImageCommand.getImageList().size());
        for(int i=0;i<saveImageCommand.getImageList().size();i++){
            MultipartFile imageFile = saveImageCommand.getImageList().get(i);
            Image image = imageDataMapper.multipartFileToImage(imageFile);
            imageDomainService.initiateImage(image);
            url.add(saveS3Image(imageFile,image));
        }
        return imageDataMapper.urlToSaveImageResponse(url);
    }

    private String saveS3Image(MultipartFile imageFile, Image image) {
        return s3Repository.save(imageFile, image);
    }
}