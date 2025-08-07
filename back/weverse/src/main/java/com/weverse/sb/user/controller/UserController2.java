package com.weverse.sb.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.user.dto.UpdateNicknameRequestDto;
import com.weverse.sb.user.service.UserService2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController2 {
	
	private final UserService2 userService;

    @PostMapping("/nickname")
    public ResponseEntity<String> updateNickname(@Valid @RequestBody UpdateNicknameRequestDto request) {
        userService.updateNickname(request.getUserId(), request.getNickname());
        return ResponseEntity.ok("success");
    }

}
