package com.weverse.sb.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.security.JwtUtil;
import com.weverse.sb.user.dto.UpdateProfileRequestDto;
import com.weverse.sb.user.dto.UserDTO;
import com.weverse.sb.user.dto.UserSettingsDto;
import com.weverse.sb.user.service.JwtUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class JwtUserController {
    private final JwtUserService jwtUserService;
    private final JwtUtil    jwtUtil;

    /**
     * 내 정보 조회
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMySettings(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token == null || !jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = jwtUtil.getEmailFromToken(token);
        UserDTO dto = jwtUserService.getUserInfoByEmail(email);
        return ResponseEntity.ok(dto);
    }
    
    // 내 정보 수정
    @PutMapping("/me")
    public ResponseEntity<UserSettingsDto> updateMySettings(
            HttpServletRequest request,
            @RequestBody @Valid UpdateProfileRequestDto updateDto) {

        String token = jwtUtil.resolveToken(request);
        if (token == null || !jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtUtil.getEmailFromToken(token);
        UserSettingsDto updated = jwtUserService.updateUserSettingsByEmail(email, updateDto);
        return ResponseEntity.ok(updated);
    }
    
} // class
