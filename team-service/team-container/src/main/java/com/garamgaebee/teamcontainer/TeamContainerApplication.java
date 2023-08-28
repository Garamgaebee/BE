package com.garamgaebee.teamcontainer;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@EnableFeignClients(basePackages = {"com.garamgaebee"})
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = { "com.garamgaebee"})
@SpringBootApplication(scanBasePackages = { "com.garamgaebee"})
@EntityScan(basePackages = { "com.garamgaebee"})
@EnableJpaAuditing
public class TeamContainerApplication {
    @PostConstruct
    public void startedTimeZoneSet() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
    public static void main(String[] args) {
        SpringApplication.run(TeamContainerApplication.class, args);
    }
}