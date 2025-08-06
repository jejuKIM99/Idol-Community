package com.weverse.sb.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.user.dto.UserDTO;
import com.weverse.sb.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/me")
	public UserDTO getMyInfo(HttpSession session) {
		log.info(">> UserController.getMyInfo()... GET");
		Long userId = (Long) session.getAttribute("userId");
		UserDTO dto = this.userService.getUserInfo(userId);
		
		return dto;
	}
	
	@PutMapping("/me/nickname")
	public String updateNickname(@RequestParam("nickname") String nickname
			, HttpSession session) {
		
		log.info(">> UserController.updateNickname()... PUT");
		
		Long userId = (Long) session.getAttribute("userId");
		String result = this.userService.updateNickname(userId, nickname);
		
		if (result.equals("true")) {
			return "success";
		} else {
			return "fail";
		}

	}
	
	@PutMapping("/me/password")
	public String updatePassword(@RequestParam("newPassword") String newPassword
			, HttpSession session) {
		
		log.info(">> UserController.updatePassword()... PUT");
		
		Long userId = (Long) session.getAttribute("userId");
		String result = this.userService.updatePassword(userId, newPassword);
		
		if (result.equals("true")) {
			return "success";
		} else {
			return "fail";
		}

	}

}
