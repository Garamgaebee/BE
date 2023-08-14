package com.garamgaebee.imagecontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = { "com.garamgaebee"})
@EntityScan(basePackages = { "com.garamgaebee"})
public class ImageContainerApplication {
    @PostConstruct
    public void startedTimeZoneSet() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
    public static void main(String[] args) {
        SpringApplication.run(ImageContainerApplication.class, args);
    }
}