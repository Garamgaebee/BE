package com.garamgaebee.imagedomaincore;

import com.garamgaebee.imagedomaincore.entity.Image;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageDomainServiceImpl implements ImageDomainService {

    @Override
    public void initiateImage(Image image) {
        image.initializeImage();
    }

    @Override
    public void deleteInitUrl(Image image) {
        image.deleteImageInitialize();
    }
}
