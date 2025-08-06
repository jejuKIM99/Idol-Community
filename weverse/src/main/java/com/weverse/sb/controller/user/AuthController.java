package com.weverse.sb.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.dto.user.JwtResponseDto;
import com.weverse.sb.dto.user.LoginRequestDto;
import com.weverse.sb.dto.user.SignupRequestDto;
import com.weverse.sb.entity.user.User;
import com.weverse.sb.service.user.AuthService;
import com.weverse.sb.util.JwtUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final JwtUtil jwtUtil;
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

            // 토큰을 반환
            return ResponseEntity.ok(new JwtResponseDto(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto signupRequest) {
        authService.signup(signupRequest);
        return ResponseEntity.ok("회원가입 성공");
    }
}
