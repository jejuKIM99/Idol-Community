package com.weverse.sb.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.security.JwtUtil;
import com.weverse.sb.user.dto.JwtResponseDto;
import com.weverse.sb.user.dto.LoginRequestDto;
import com.weverse.sb.user.dto.SignupRequestDto;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final JwtUtil jwtUtil;
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());

            // 토큰과 userId를 함께 반환
            return ResponseEntity.ok(new JwtResponseDto(token, user.getUserId()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 실패 시 body는 비워서 보냄
        }
    }
    

    @PostMapping("/check-email")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        boolean exists = authService.checkEmailExists(email);
        Map<String, Object> result = new HashMap<>();
        result.put("exists", exists);
        return ResponseEntity.ok(result);
    }
    

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequestDto signupRequest) {
        authService.signup(signupRequest);
        return ResponseEntity.ok("회원가입 성공");
    }
}
