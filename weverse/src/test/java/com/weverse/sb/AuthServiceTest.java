package com.weverse.sb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.dto.user.SignupRequestDto;
import com.weverse.sb.entity.user.User;
import com.weverse.sb.repository.user.UserRepository;
import com.weverse.sb.service.user.AuthService;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)  // ⭐️ 시큐리티 필터 비활성화
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Test
    void checkEmailExists_withExistingEmail_returnsTrue() {
        // given
        User user = User.builder().email("exist@a.com").build();
        userRepository.save(user);

        // when
        boolean exists = authService.checkEmailExists("exist@a.com");

        // then
        assertTrue(exists);
    }

    @Test
    void checkEmailExists_withNotExistingEmail_returnsFalse() {
        boolean exists = authService.checkEmailExists("nobody@a.com");
        assertFalse(exists);
    }
    
    @Test
    void 회원가입_정상_동작() {
        // given
        SignupRequestDto request = new SignupRequestDto("test@email.com", "test1234!");

        // when
        authService.signup(request);

        // then
        User user = userRepository.findByEmail("test@email.com").orElseThrow();
        assertEquals("test@email.com", user.getEmail());
        assertTrue(user.getPassword().startsWith("$2a$")); // bcrypt로 암호화 되었는지
        assertNotNull(user.getNickname());
    }

    @Test
    void 중복_이메일_회원가입_실패() {
        // given
        String email = "duplicate@email.com";
        userRepository.save(User.builder()
                .email(email)
                .password("encoded_pw")
                .nickname("user1234")
                .build());

        SignupRequestDto request = new SignupRequestDto(email, "test1234!");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            authService.signup(request);
        });
    }
    
}
