package com.garamgaebee.imagedomaincore;


import com.garamgaebee.imagedomaincore.entity.Image;

import java.util.List;

public interface ImageDomainService {

    void initiateImage(Image image);

    void deleteInitUrl(Image image);
}
