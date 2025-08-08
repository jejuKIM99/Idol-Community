package com.weverse.sb.user.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.user.dto.UpdateProfileRequestDto;
import com.weverse.sb.user.dto.UserSettingsDto;
import com.weverse.sb.user.dto.UserDTO;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
   
    //  현재 로그인한 사용자의 설정 정보 조회
    public UserSettingsDto getUserSettingsByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. email=" + email));

        return new UserSettingsDto(
            user.getUserId(),
            user.getEmail(),
            user.getNickname(),
            user.getName(),
            user.getPassword() != null,       // 비밀번호 설정 여부
            user.getCountry(),
            user.getIsSmsVerified(),
            user.getCreatedAt().toLocalDate() // 가입일
        );
    }
    
    // 내 정보 조회 (UserDTO 반환)
    public UserDTO getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. email=" + email));

        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .country(user.getCountry())
                .profileImage(user.getProfileImage())
                .jellyBalance(user.getJellyBalance())
                .cashBalance(user.getCashBalance())
                .isEmailVerified(user.getIsEmailVerified())
                .isSmsVerified(user.getIsSmsVerified())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // 내 정보 변경
    @Transactional
    public UserSettingsDto updateUserSettingsByEmail(String email, UpdateProfileRequestDto dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. email=" + email));

        // 변경 가능한 필드만 업데이트
        if (dto.getNickname() != null) user.setNickname(dto.getNickname());
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(user);

        return new UserSettingsDto(
            user.getUserId(),
            user.getEmail(),
            user.getNickname(),
            user.getName(),
            user.getPassword() != null,
            user.getCountry(),
            user.getIsSmsVerified(),
            user.getCreatedAt().toLocalDate()
        );
    }
    
} // class