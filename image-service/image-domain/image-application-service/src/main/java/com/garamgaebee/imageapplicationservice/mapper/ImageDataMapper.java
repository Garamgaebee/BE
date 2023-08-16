package com.garamgaebee.imageapplicationservice.mapper;

import com.garamgaebee.imageapplicationservice.dto.DeleteImageResponse;
import com.garamgaebee.imageapplicationservice.dto.SaveImageCommand;
import com.garamgaebee.imageapplicationservice.dto.SaveImageResponse;
import com.garamgaebee.imagedomaincore.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageDataMapper {
    public SaveImageResponse urlToSaveImageResponse(List<String> imageUrlList) {
        return new SaveImageResponse(imageUrlList);
    }
    public Image multipartFileToImage(MultipartFile multipartFile) {
        return Image.builder()
                .originUrl(multipartFile.getOriginalFilename())
                .contentType(multipartFile.getContentType()).build();
    }

    public Image urlToImage(String urls) {
        return  Image.builder()
                .originUrl(urls).build();
    }

    public DeleteImageResponse booleanListToDeleteImageResponse(List<Boolean> isDeletedLists) {
        return new DeleteImageResponse(isDeletedLists);
    }
}