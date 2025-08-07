package com.weverse.sb.user.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.user.dto.SignupRequestDto;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.enums.Role;
import com.weverse.sb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    // 이메일 체크
    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    // 회원가입
    @Transactional
    public void signup(SignupRequestDto request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 닉네임 자동생성 (ex. "user1234")
        String nickname = "user" + System.currentTimeMillis();

        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(nickname)
                .role(Role.ROLE_USER)
                .name("회원")                     // ✅ 더미 값
                .phoneNumber("010-0000-0000")     // ✅ 더미 값
                .country("KR")                    // ✅ 더미 값
                .jellyBalance(0)                  // ✅ NOT NULL
                .cashBalance(0)                   // ✅ NOT NULL
                .isEmailVerified(false)           // ✅ NOT NULL
                .isSmsVerified(false)             // ✅ NOT NULL
                .createdAt(LocalDateTime.now())   // ✅ NOT NULL
                .build();

        userRepository.save(user);
    }
    
    // 로그인
    public User login(String email, String password) {
        // 1. 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일입니다."));

        // 평문 vs 암호화된 비번 비교!
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
    
}
