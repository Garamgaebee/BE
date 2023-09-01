package com.garamgaebee.auth.service.messaging;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients({"com.garamgaebee"})
public class OpenFeignConfig {
}
