package com.garamgaebee.notification.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//TODO dataaccess layer 생성 후 추가
//@EnableJpaRepositories(basePackages = { "com.garamgaebee"})
//@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.garamgaebee")
@EntityScan(basePackages = { "com.garamgaebee"})
@EnableDiscoveryClient
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
