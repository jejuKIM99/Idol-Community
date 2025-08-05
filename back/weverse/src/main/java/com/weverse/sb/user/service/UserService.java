package com.weverse.sb.user.service;

import com.weverse.sb.user.dto.UserDTO;

public interface UserService {

	// 유저 상세정보 조회
	UserDTO getUserInfo(Long userId);
	
	// 유저 닉네임 변경
	String updateNickname(Long userId, String newNickname);
	
	// 유저 비밀번호 변경
	String updatePassword(Long userId, String newPassword);
	
}
