package com.weverse.sb.userTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weverse.sb.user.dto.UserSettingsDto;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;
import com.weverse.sb.user.service.JwtUserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtUserService jwtUserService;

    @Test
    void 이메일_기반_유저_설정_조회_성공() {
        // GIVEN
        String email = "test@test.com";
        User user = User.builder()
                .userId(1L)
                .email(email)
                .nickname("tester")
                .name("테스터")
                .password("encodedPwd")
                .country("대한민국")
                .isSmsVerified(true)
                .createdAt(LocalDateTime.of(2025, 7, 30, 10, 0))
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // WHEN
        UserSettingsDto dto = jwtUserService.getUserSettingsByEmail(email);

        // THEN
        assertThat(dto.getUserId()).isEqualTo(1L);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getNickname()).isEqualTo("tester");
        assertThat(dto.isSmsVerified()).isTrue();
        assertThat(dto.getSignupDate()).isEqualTo(java.time.LocalDate.of(2025, 7, 30));
    }
}

