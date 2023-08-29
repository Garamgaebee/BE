package com.garamgaebee.teamcontainer;

import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.TeamDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TeamDomainService orderDomainService() {
        return new TeamDomainServiceImpl();
    }
}