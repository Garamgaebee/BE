package com.garamgaebee.images3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.images3.entity.S3Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3BucketRepository {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.path}")
    private String path;
    @Value("${cloud.aws.front-cloud.distribution}")
    private String frontCloudDistribution;

    public String uploadImage(S3Image s3Image) {
        String fileName = s3Image.getImageUrl();
        String contentType = s3Image.getContentType();
        fileName = path + "/" + fileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        try {
            byte[] bytes = IOUtils.toByteArray(s3Image.getImageFile().getInputStream());
            metadata.setContentLength(bytes.length);
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, s3Image.getImageFile().getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.error("image s3 upload fail: {}", e.getMessage());
            throw new BaseException(BaseErrorCode.SERVER_ERROR);
        }
        return frontCloudDistribution + amazonS3.getUrl(bucket, fileName).getPath();
    }

    public boolean deleteImage(String url) {
        boolean isObjectExist = amazonS3.doesObjectExist(bucket, path + "/" + url);
        if (isObjectExist) {
            amazonS3.deleteObject(bucket, path + "/" + url);
        } else {
            return false;
        }
        return true;
    }

    public boolean checkExistObject(String url) {
        return amazonS3.doesObjectExist(bucket, path + "/" + url);
    }
}