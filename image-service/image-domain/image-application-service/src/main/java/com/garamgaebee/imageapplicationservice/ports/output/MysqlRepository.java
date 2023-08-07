package com.garamgaebee.imageapplicationservice.ports.output;

import com.garamgaebee.imagedomaincore.entity.Image;

public interface MysqlRepository {
    Long save(Image image);
}
