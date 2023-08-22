package com.garamgaebee.gateway.service.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.gateway.service.util.JwtUtil;
import com.garamgaebee.gateway.service.util.TokenUser;
import lombok.Setter;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationGatewayFilterFactory extends
        AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config> {

    private static final String ROLE_KEY = "role";

    private final JwtUtil jwtUtil;

    public JwtAuthenticationGatewayFilterFactory(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(ROLE_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!containsAuthorization(request)) {
                return onError(response, BaseErrorCode.EMPTY_ACCESS_TOKEN);
            }

            String token = extractToken(request);
            if (!jwtUtil.validateToken(token)) {
                return onError(response, BaseErrorCode.INVALID_ACCESS_TOKEN);
            }

            TokenUser tokenUser = jwtUtil.decode(token);
            if (!hasRole(tokenUser, config.role)) {
                return onError(response, BaseErrorCode.FORBIDDEN_ACCESS);
            }

            addAuthorizationHeaders(request, tokenUser);

            return chain.filter(exchange);
        };
    }

    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0).substring(7); // Bearer 제거
    }

    private boolean hasRole(TokenUser tokenUser, String role) {
        if(tokenUser.getRole().stream().anyMatch(targetRole -> targetRole.equals(role)))
            return true;
        return false;
    }

    private void addAuthorizationHeaders(ServerHttpRequest request, TokenUser tokenUser) {
        request.mutate()
                .header("X-Authorization-Id", tokenUser.getId())
                .header("X-Authorization-Role", tokenUser.getRole().toString())
                .build();
    }

    @Setter
    public static class Config {

        private String role;

    }

    private Mono<Void> onError(ServerHttpResponse response, BaseErrorCode errorCode) {
        DataBuffer buffer = response.bufferFactory().wrap(errorResponseMaker(errorCode).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    private String errorResponseMaker(BaseErrorCode baseErrorCode) {
        return "{\n" +
                "\t\"code\":\"" + baseErrorCode.getCode() + "\",\n" +
                "\t\"message\":\"" + baseErrorCode.getMessage() + "\"\n" +
                "}";
    }

}