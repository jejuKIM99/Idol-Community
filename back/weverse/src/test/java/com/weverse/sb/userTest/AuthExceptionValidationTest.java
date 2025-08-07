package com.weverse.sb.userTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weverse.sb.security.JwtUtil;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.enums.Role;
import com.weverse.sb.user.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
@AutoConfigureMockMvc
class AuthExceptionValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private String jwtToken;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    	if (!userRepository.existsByEmail("test@test.com")) {
        User user = User.builder()
                .email("test@test.com")
                .password(passwordEncoder.encode("test1234"))
                .nickname("tester")
                .role(Role.ROLE_USER)
                .name("Dummy User")            // NOT NULL
                .phoneNumber("010-0000-0000")  // NOT NULL
                .country("KR")                 // NOT NULL
                .jellyBalance(0)               // NOT NULL
                .cashBalance(0)                // NOT NULL
                .isEmailVerified(false)        // NOT NULL
                .isSmsVerified(false)          // NOT NULL
                .createdAt(LocalDateTime.now())// NOT NULL
                .build();
        userRepository.save(user);
        
        jwtToken = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
    }
    }
    @Test
    void 회원가입_이메일형식_오류_검증실패() throws Exception {
        String invalidJson = "{\"email\": \"invalid-email\", \"password\": \"12345678\"}";

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("입력값 검증 실패"))
                .andExpect(jsonPath("$.errors[0].field").value("email"));
    }

    @Test
    void 로그인_비밀번호_누락_검증실패() throws Exception {
        String invalidJson = "{\"email\": \"test@test.com\"}";

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("password"));
    }

    @Test
    void 프로필수정_닉네임_길이초과_검증실패() throws Exception {
        String longNickname = "a".repeat(21);
        String invalidJson = "{\"nickname\": \"" + longNickname + "\"}";

        mockMvc.perform(put("/api/user/me")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("nickname"));
    }

    @Test
    void 로그인_존재하지않는_이메일() throws Exception {
        String json = "{\"email\": \"notfound@test.com\", \"password\": \"somepassword\"}";

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("유저를 찾을 수 없습니다."));
    }

    @Test
    void 만료된_토큰_요청시_401() throws Exception {
    	// 애플리케이션과 같은 Base64 비밀키 문자열
    	String base64Secret = "88YBekx5vqcDnTS3PGf2h8pV224Yad/fIJta1VpJbZN/6vIUM4Kl9GXSdA5GlvsT/ztdrvQ5hiZOzhrBiilQ+A==";
    	Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));

    	String expiredToken = Jwts.builder()
    	        .setSubject("test@test.com")
    	        .claim("role", "USER")                            // role 클레임도 넣어 줘야 getRoleFromToken() 통과
    	        .setExpiration(Date.from(Instant.now().minusSeconds(3600)))
    	        .signWith(key, SignatureAlgorithm.HS512)
    	        .compact();

        mockMvc.perform(get("/api/user/me")
                        .header("Authorization", "Bearer " + expiredToken))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("토큰이 만료되었습니다."));
    }
}