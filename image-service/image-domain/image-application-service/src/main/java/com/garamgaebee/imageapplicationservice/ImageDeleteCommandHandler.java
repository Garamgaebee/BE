package com.garamgaebee.imageapplicationservice;

import com.garamgaebee.imageapplicationservice.dto.DeleteImageCommand;
import com.garamgaebee.imageapplicationservice.dto.DeleteImageResponse;
import com.garamgaebee.imageapplicationservice.mapper.ImageDataMapper;
import com.garamgaebee.imageapplicationservice.ports.output.S3Repository;
import com.garamgaebee.imagedomaincore.ImageDomainService;
import com.garamgaebee.imagedomaincore.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageDeleteCommandHandler {
    private final ImageDomainService imageDomainService;
    private final ImageDataMapper imageDataMapper;
    private final S3Repository s3Repository;
    public DeleteImageResponse deleteImage(DeleteImageCommand deleteImageCommand) {
        List<String> urls = deleteImageCommand.getUrlList();
        List<Image> urlList = new ArrayList<>(urls.size());
        for(int i=0;i<urls.size();i++){
            Image image = imageDataMapper.urlToImage(urls.get(i));
            imageDomainService.deleteInitUrl(image);
            urlList.add(image);
        }
        List<Boolean> isDeletedLists = s3Repository.listDelete(urlList);
        return imageDataMapper.booleanListToDeleteImageResponse(isDeletedLists);
    }
}
