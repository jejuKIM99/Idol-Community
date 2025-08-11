package com.weverse.sb.userTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.security.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
@AutoConfigureMockMvc
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    private Key getSigningKey() {
        String secretKey = "q1W2e3R4t5Y6u7I8o9P0aS1dF2gH3jK4"; // ⚠️ application.yml의 jwt.secret과 일치해야 함
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void 만료된_토큰이면_ExpiredJwtException_던짐() throws InterruptedException {
        // GIVEN: 1초 만료 토큰 생성
        String expiredToken = Jwts.builder()
                .setSubject("test@test.com")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000)) // 1초짜리
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        Thread.sleep(1500); // 1.5초 대기 → 만료 유도

        // WHEN & THEN
        assertThrows(ExpiredJwtException.class, () -> {
            jwtUtil.validateTokenAndGetEmail(expiredToken);
        });
    }

    @Test
    void 잘못된_토큰이면_JwtException_던짐() {
        // GIVEN: 가짜 토큰
        String invalidToken = "abc.def.ghi";

        // WHEN & THEN
        assertThrows(JwtException.class, () -> {
            jwtUtil.validateTokenAndGetEmail(invalidToken);
        });
    }
}


