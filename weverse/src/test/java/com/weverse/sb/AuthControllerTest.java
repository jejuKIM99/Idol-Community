package com.weverse.sb;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weverse.sb.dto.user.LoginRequestDto;
import com.weverse.sb.entity.user.User;
import com.weverse.sb.repository.user.UserRepository;

// @WebMvcTest(AuthController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)  // ⭐️ 시큐리티 필터 비활성화
@Transactional
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    /*
    @MockBean
    private AuthService authService;
    */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
    @Test
    void checkEmailExists_true() throws Exception {
        String email = "test@example.com";
        Mockito.when(authService.checkEmailExists(email)).thenReturn(true);

        mockMvc.perform(post("/api/auth/check-email")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"" + email + "\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.exists").value(true));
    }

    @Test
    void checkEmailExists_false() throws Exception {
        String email = "noone@none.com";
        Mockito.when(authService.checkEmailExists(email)).thenReturn(false);

        mockMvc.perform(post("/api/auth/check-email")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"" + email + "\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.exists").value(false));
    }
    */
       // 로그인 전용 테스트용 계정 저장
    @BeforeEach
    void setUp() {
        User user = User.builder()
            .email("test@test.com")
            .password(passwordEncoder.encode("test1234"))
            .nickname("testUser")
            .role("USER")
            .build();
        userRepository.save(user);
    }
	
    @Test
    void 로그인_성공시_JWT_토큰_반환() throws Exception {
        // GIVEN (이미 회원가입되어 있다고 가정)
        String email = "test@test.com";
        String password = "test1234";

        String loginJson = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);

        // WHEN & THEN
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void 로그인_실패_이메일_없음() throws Exception {
        LoginRequestDto loginRequest = new LoginRequestDto("none@none.com", "test1234");
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void 로그인_실패_비밀번호_틀림() throws Exception {
        LoginRequestDto loginRequest = new LoginRequestDto("test@test.com", "wrongpw");
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void 로그아웃_성공() throws Exception {
        // 세션을 먼저 생성(로그인) 후, 로그아웃 요청을 테스트
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", 1L);

        mockMvc.perform(post("/api/auth/logout").session(session))
            .andExpect(status().isOk())
            .andExpect(content().string("로그아웃 성공"));
    }
}
