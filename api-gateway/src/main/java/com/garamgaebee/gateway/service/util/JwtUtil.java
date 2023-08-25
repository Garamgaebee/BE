package com.garamgaebee.gateway.service.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    /**
     * token payload 추출
     */
    public TokenUser decode(String token) {
        try {
            return TokenUser.builder()
                    .id(Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token).getBody().getSubject())
                    .role((List<String>)Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token).getBody().get("roles"))
                    .build();
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
    }

    /**
     * token 검증
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
