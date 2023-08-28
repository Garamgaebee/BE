package com.garamgaebee.gateway.service.util;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/auth/v3/api-docs").uri("http://auth-service"))
                .route(r -> r.path("/members/v3/api-docs").uri("http://member-service"))
                .route(r -> r.path("/teams/v3/api-docs").uri("http://team-service"))
                .route(r -> r.path("/threads/v3/api-docs").uri("http://thread-service"))
                .route(r -> r.path("/search/v3/api-docs").uri("http://search-service"))
    .build();
    }
}
