package com.weverse.sb.userTest;


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

import com.weverse.sb.config.JwtUtil;
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
