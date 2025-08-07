package com.weverse.sb.userTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.user.dto.SignupRequestDto;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;
import com.weverse.sb.user.service.AuthService;



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
        User user = User.builder()
        		.email("exist@a.com")
        		.password("dummy1234")         // NOT NULL
                .name("Dummy User")            // NOT NULL
                .nickname("dummyNick")         // NOT NULL
                .phoneNumber("010-0000-0000")  // NOT NULL
                .country("KR")                 // NOT NULL
                .jellyBalance(0)               // NOT NULL
                .cashBalance(0)                // NOT NULL
                .isEmailVerified(false)        // NOT NULL
                .isSmsVerified(false)          // NOT NULL
                .createdAt(LocalDateTime.now())// NOT NULL
        		.build();
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
                .name("회원")                    // ✅ 추가
                .phoneNumber("010-0000-0000")   // ✅ 추가
                .country("KR")                  // ✅ 추가
                .jellyBalance(0)
                .cashBalance(0)
                .isEmailVerified(false)
                .isSmsVerified(false)
                .createdAt(LocalDateTime.now()) // ✅ 추가
                .build());

        SignupRequestDto request = new SignupRequestDto(email, "test1234!");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            authService.signup(request);
        });
    }

    
}
