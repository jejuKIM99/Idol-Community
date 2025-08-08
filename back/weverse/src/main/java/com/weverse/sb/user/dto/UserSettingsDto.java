package com.weverse.sb.user.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsDto {
	
	private Long    userId;
    private String  email;
    private String  nickname;
    private String  name;
    private boolean passwordSet;            // 비밀번호 설정 여부
    private String country;                 // 국가
    private boolean smsVerified;            // SMS 인증(휴대폰 인증) 여부
    
    private LocalDate signupDate;           // 가입일


}
