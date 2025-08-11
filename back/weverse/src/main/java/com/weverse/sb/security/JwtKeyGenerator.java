package com.weverse.sb.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;

import java.security.Key;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // HS512용 512비트 이상 키 생성
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Base64 문자열로 변환
        String base64Key = Encoders.BASE64.encode(key.getEncoded());

        // 출력해 두기 (콘솔 복사)
        System.out.println("JWT_SECRET=" + base64Key);
    }
}

