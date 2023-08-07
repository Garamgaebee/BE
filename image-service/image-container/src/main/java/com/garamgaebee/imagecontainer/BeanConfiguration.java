package com.garamgaebee.imagecontainer;

import com.garamgaebee.imagedomaincore.ImageDomainService;
import com.garamgaebee.imagedomaincore.ImageDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ImageDomainService orderDomainService() {
        return new ImageDomainServiceImpl();
    }
}
