package com.weverse.sb.userTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


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

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Long savedUserId;

    @BeforeEach
    void setUp() {
        String email = "test@test.com";

        // 이미 있는 경우는 가져오고, 없으면 새로 생성
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode("test1234"))
                    .nickname("oldNickname")
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
            return userRepository.save(newUser); // 저장하고 반환
        });

        savedUserId = user.getUserId(); // ✅ 이후 테스트에서 필요하므로 저장

    }

    @Test
    void 닉네임_변경_성공() throws Exception {
        String requestJson = String.format("""
            {
                "userId": %d,
                "nickname": "newNickname"
            }
            """, savedUserId);

        mockMvc.perform(post("/api/nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));

        // 실제로 닉네임이 바뀌었는지 확인
        User updated = userRepository.findById(savedUserId).orElseThrow();
        assertEquals("newNickname", updated.getNickname());
    }

    @Test
    void 닉네임_중복이면_에러() throws Exception {
        // 중복 닉네임 미리 저장
        userRepository.save(User.builder()
        		.email("other@test.com")
                .password(passwordEncoder.encode("test1234"))
                .nickname("duplicatedNick")       // 이 닉네임이 중복됨
                .role(Role.ROLE_USER)
                .name("Other User")               // 필수값
                .phoneNumber("010-1111-1111")     // 필수값
                .country("KR")                    // 필수값
                .jellyBalance(0)
                .cashBalance(0)
                .isEmailVerified(false)
                .isSmsVerified(false)
                .createdAt(LocalDateTime.now())
                .build());

        String requestJson = String.format("""
            {
                "userId": %d,
                "nickname": "duplicatedNick"
            }
            """, savedUserId);

        mockMvc.perform(post("/api/nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 닉네임_공백이면_에러() throws Exception {
        String requestJson = String.format("""
            {
                "userId": %d,
                "nickname": " "
            }
            """, savedUserId);

        mockMvc.perform(post("/api/nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}

/*
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.weverse.sb.security.JwtUtil;
import com.weverse.sb.user.dto.UserSettingsDto;
import com.weverse.sb.user.service.JwtUserService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 비활성화
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUserService jwtUserService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void 내정보_조회_API_성공_이메일기반() throws Exception {
        // GIVEN
        String email = "test@test.com";
        UserSettingsDto dto = new UserSettingsDto(
                1L,
                email,
                "tester",
                "테스터",
                true,
                "대한민국",
                true,
                LocalDate.of(2025, 7, 30)
        );

        // JWT 동작 Mock
        when(jwtUtil.resolveToken(org.mockito.ArgumentMatchers.any())).thenReturn("fakeToken");
        when(jwtUtil.isTokenValid("fakeToken")).thenReturn(true);
        when(jwtUtil.getEmailFromToken("fakeToken")).thenReturn(email);

        // 서비스 Mock
        when(jwtUserService.getUserSettingsByEmail(anyString())).thenReturn(dto);

        // WHEN & THEN
        mockMvc.perform(get("/api/user/me")
                        .header("Authorization", "Bearer fakeToken")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.nickname").value("tester"))
                .andExpect(jsonPath("$.smsVerified").value(true));
    }
}
*/