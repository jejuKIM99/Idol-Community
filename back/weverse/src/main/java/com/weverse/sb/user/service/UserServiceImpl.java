package com.weverse.sb.user.service;

import org.springframework.stereotype.Service;

import com.weverse.sb.user.dto.UserDTO;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;

    @Override
    public UserDTO getUserInfo(Long userId) { 
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO dto = UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .country(user.getCountry())
                .profileImage(user.getProfileImage())
                .jellyBalance(user.getJellyBalance() != null ? user.getJellyBalance() : 0)
                .cashBalance(user.getCashBalance())
                .isEmailVerified(user.getIsEmailVerified())
                .isSmsVerified(user.getIsSmsVerified())
                .createdAt(user.getCreatedAt())
                .build();
        
        System.out.println(">> dto 값: " + dto);
        
        return dto;
    }

	@Override
	public String updateNickname(Long userId, String newNickname) {
		
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		String currentNickname = user.getNickname();
		
		if (!newNickname.equals(currentNickname)) {
	        user.setNickname(newNickname);
	        this.userRepository.save(user);
	        return "true";  // 변경이 실제로 일어난 경우
	    }

		return "false";
	}

	@Override
	public String updatePassword(Long userId, String newPassword) {
		
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		String currentPassword = user.getPassword();
		
		if (!newPassword.equals(currentPassword)) {
	        user.setPassword(newPassword);
	        this.userRepository.save(user);
	        return "true";  // 변경이 실제로 일어난 경우
	    }
		
		return "false";
	}


    
    
	
}
