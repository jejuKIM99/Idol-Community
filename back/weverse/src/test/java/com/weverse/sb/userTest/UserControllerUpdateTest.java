package com.weverse.sb.userTest;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
@AutoConfigureMockMvc(addFilters = false)
class UserControllerUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUserService jwtUserService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void 내정보_수정_API_성공_닉네임_이름_비밀번호() throws Exception {
        // GIVEN
        String email = "test@test.com";

        UserSettingsDto updatedDto = new UserSettingsDto(
                1L,
                email,
                "newNickname",          // 변경된 닉네임
                "새이름",                 // 변경된 이름
                true,
                "대한민국",
                true,
                LocalDate.of(2025, 7, 30)
        );

        // JWT Mock
        when(jwtUtil.resolveToken(any())).thenReturn("fakeToken");
        when(jwtUtil.isTokenValid("fakeToken")).thenReturn(true);
        when(jwtUtil.getEmailFromToken("fakeToken")).thenReturn(email);

        // Service Mock
        when(jwtUserService.updateUserSettingsByEmail(any(), any())).thenReturn(updatedDto);

        String json = """
        {
          "nickname": "newNickname",
          "name": "새이름",
          "password": "newPassword1234"
        }
        """;

        // WHEN & THEN
        mockMvc.perform(put("/api/user/me")
                        .header("Authorization", "Bearer fakeToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("newNickname"))
                .andExpect(jsonPath("$.name").value("새이름"));
    }
}

