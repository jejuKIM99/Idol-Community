package com.weverse.sb.service.user;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weverse.sb.dto.user.UserSettingsDto;
import com.weverse.sb.entity.user.User;
import com.weverse.sb.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 현재 로그인한 사용자의 설정 정보 조회
     */
    public UserSettingsDto getUserSettings(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. id=" + userId));
        return new UserSettingsDto(
            user.getUserId(),
            user.getEmail(),
            user.getNickname(),
            user.getName(),
            user.getPassword() != null,                // 비밀번호 설정 여부
            user.getCountry(),
            user.getIsSmsVerified(),
            user.getCreatedAt().toLocalDate()          // 가입일
        );
    }
}