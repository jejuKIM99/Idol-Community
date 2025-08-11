package com.weverse.sb.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.user.dto.AddressResponseDto;
import com.weverse.sb.user.dto.CreateAddressRequestDto;
import com.weverse.sb.user.dto.UpdateNicknameRequestDto;
import com.weverse.sb.user.service.AddressService;
import com.weverse.sb.user.service.UserService2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController2 {
	
	private final UserService2 userService;
	
	private final AddressService addressService;

    @PostMapping("/nickname")
    public ResponseEntity<String> updateNickname(@Valid @RequestBody UpdateNicknameRequestDto request) {
        userService.updateNickname(request.getUserId(), request.getNickname());
        return ResponseEntity.ok("success");
    }
    

    // 현재 로그인 사용자의 이메일을 CustomUserDetails#getUsername()으로 받는다고 가정 (확실하지 않음)
    @PostMapping("/users/me/addresses")
    public AddressResponseDto createMyAddress(@AuthenticationPrincipal Object principal,
                                           @Valid @RequestBody CreateAddressRequestDto request) {
        String email = extractEmail(principal); // 아래 헬퍼 메서드 참고
        return addressService.createMyAddress(email, request);
    }

    @GetMapping("/users/me/addresses")
    public List<AddressResponseDto> getMyAddresses(
            @AuthenticationPrincipal(expression = "username") String email // ✅ 깔끔
    ) {
        return addressService.getMyAddresses(email);
    }

    // === 헬퍼: 프로젝트의 CustomUserDetails 구조에 맞춰 수정하세요 ===
    private String extractEmail(Object principal) {
        // 확실하지 않음: 팀의 CustomUserDetails 타입과 username 필드가 email인지 확인 필요
        // 예시 1) CustomUserDetails 타입
        /*
        CustomUserDetails cud = (CustomUserDetails) principal;
        return cud.getUsername(); // email
        */

        // 예시 2) 기본 UserDetails 사용
        /*
        UserDetails ud = (UserDetails) principal;
        return ud.getUsername();
        */

        // 임시 안전장치: SecurityContext에서 꺼내는 방식 (필요시 교체)
        return org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getName();
    }

}
