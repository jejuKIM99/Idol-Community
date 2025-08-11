package com.weverse.sb.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.weverse.sb.user.enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final String secretKey;
    private final long expirationTime;
    private final Key key;

    // 생성자 주입으로 secretKey 받아와서 Key 객체 만듦
    @Autowired
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
        this.expirationTime = 1000 * 60 * 60 * 24; // 1일 (필요하면 @Value로 분리 가능)
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 토큰 생성
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // 이메일을 subject에 저장
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // 이메일 추출
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 권한 추출
    public Role getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Role.valueOf(claims.get("role", String.class));
    }

    // 토큰 유효성 검사
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    /*
    // ✅ 토큰 유효성 검사 + 이메일 추출 + 예외 던짐
    public String validateTokenAndGetEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();

        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new JwtException("유효하지 않은 토큰입니다.");
        }
    }
	*/
    public String validateTokenAndGetEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)    // 만료면 여기서 ExpiredJwtException
                .getBody();
        return claims.getSubject();
    }

    
    // 요청 헤더에서 Authorization 값 접두어 "Bearer " 제거
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 제거
        }
        return null; // 토큰 없음
    }
}
