package com.weverse.sb.userTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.enums.Role;
import com.weverse.sb.user.repository.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // 테스트 유저 저장 (email, password, role 등)
        User user = User.builder()
            .email("test@test.com")
            .password(passwordEncoder.encode("test1234")) // ✅ 암호화된 비번
            .nickname("testUser")
            .role(Role.ROLE_USER)
            .name("테스트")
            .phoneNumber("01012345678")
            .country("KR")
            .jellyBalance(0)
            .cashBalance(0)
            .isEmailVerified(false)
            .isSmsVerified(false)
            .createdAt(LocalDateTime.now())
            .build();

        userRepository.save(user);
    }

    @Test
    void 로그인_성공시_JWT_토큰_반환() throws Exception {
        // 로그인 요청 JSON 구성
        String loginJson = """
            {
                "email": "test@test.com",
                "password": "test1234"
            }
        """;

        // 요청 및 응답 검증
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void 로그인_실패시_401_응답() throws Exception {
        String loginJson = """
            {
                "email": "test@test.com",
                "password": "wrongpassword"
            }
        """;

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
            .andExpect(status().isUnauthorized());
    }
}

