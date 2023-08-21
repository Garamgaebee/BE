package com.garamgaebee.gateway.service.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.common.exception.ErrorDTO;
import com.garamgaebee.gateway.service.util.JwtUtil;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final JwtUtil jwtUtil;

    public AuthorizationHeaderFilter(JwtUtil jwtUtil) {

        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
        // application.yml 파일에서 지정한 filer의 Argument값을 받는 부분
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().get("Authorization").get(0).substring(7);   // 헤더의 토큰 파싱 (Bearer 제거)

            // jwt token 검증
            if(!jwtUtil.validateToken(token)) {
                throw new BaseException(BaseErrorCode.INVALID_ACCESS_TOKEN);
            }

            return chain.filter(exchange);
        };
    }

    // 토큰 검증 요청을 실행하는 도중 예외가 발생했을 때 예외처리하는 핸들러
    @Bean
    public ErrorWebExceptionHandler tokenValidation() {
        return new JwtTokenExceptionHandler();
    }

    // 실제 토큰이 null, 만료 등 예외 상황에 따른 예외처리
    public class JwtTokenExceptionHandler implements ErrorWebExceptionHandler {

        private String errorResponseMaker(BaseErrorCode baseErrorCode) {
            return "{\n" +
                    "\t\"code\":\"" + baseErrorCode.getCode() + "\",\n" +
                    "\t\"message\":\"" + baseErrorCode.getMessage() + "\"\n" +
                    "}";

        }

        @Override
        public Mono<Void> handle(
                ServerWebExchange exchange, Throwable ex) {

            ServerHttpResponse response = exchange.getResponse();
            ObjectMapper objectMapper = new ObjectMapper();

            BaseErrorCode errorCode = BaseErrorCode.INVALID_ACCESS_TOKEN;
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            if(ex.getClass() == NullPointerException.class) {
                errorCode = BaseErrorCode.EMPTY_ACCESS_TOKEN;
            }

            byte[] bytes = errorResponseMaker(errorCode).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
    }
}