package com.garamgaebee.images3.adapter;


import com.garamgaebee.imageapplicationservice.ports.output.S3Repository;
import com.garamgaebee.imagedomaincore.entity.Image;
import com.garamgaebee.images3.S3BucketRepository;
import com.garamgaebee.images3.mapper.S3ImageDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3RepositoryImpl implements S3Repository {
    private final S3BucketRepository s3BucketRepository;
    private final S3ImageDataMapper s3ImageDataMapper;
    @Override
    public String save(MultipartFile imageFile, Image image) {
        return s3BucketRepository.uploadImage(s3ImageDataMapper.ImageToS3Image(imageFile, image));
    }

    @Override
    public List<Boolean> listDelete(List<Image> urlList) {
        boolean flag = false;
        List<Boolean> isDeleted = new ArrayList<>(urlList.size());
        for(int i=0;i< urlList.size();i++){
            if(!s3BucketRepository.checkExistObject(urlList.get(i).getId().getValue())){
                flag = true;
                isDeleted.add(false);
            }
            else {
                isDeleted.add(true);
            }
        }
        if(flag){
            return isDeleted;
        }
        else{
            isDeleted.clear();
            for(int i=0;i< urlList.size();i++){
                s3BucketRepository.deleteImage(urlList.get(i).getId().getValue());
                isDeleted.add(true);
            }
        }
        return isDeleted;
    }

}
