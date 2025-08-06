package com.weverse.sb.controller.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.dto.user.UserSettingsDto;
import com.weverse.sb.service.user.UserService;
import com.weverse.sb.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil    jwtUtil;

    /**
     * 내 정보 조회
     */
    @GetMapping("/me")
    public ResponseEntity<UserSettingsDto> getMySettings(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = jwtUtil.getUserId(token);
        UserSettingsDto dto = userService.getUserSettings(userId);
        return ResponseEntity.ok(dto);
    }
}
