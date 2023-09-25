package com.garamgaebee.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;

@OpenAPIDefinition
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${openapi.service.title}") String serviceTitle,
            @Value("${openapi.service.version}") String serviceVersion,
            @Value("${openapi.service.url}") String url) {

        // SecuritySecheme명
        String jwtSchemeName = "Authorization";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        return new OpenAPI()
                .servers(List.of(new Server().url(url)))
                .info(new Info().title(serviceTitle).version(serviceVersion))
                .addSecurityItem(securityRequirement)
                .components(
                        new Components()
                                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                                        .name(jwtSchemeName)
                                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                                        .scheme("bearer")
                                        .bearerFormat("JWT")) // 토큰 형식을 지정하는 임의의 문자(Optional)
                );
    }

}
