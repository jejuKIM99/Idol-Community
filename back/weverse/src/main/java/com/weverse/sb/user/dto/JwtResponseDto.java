package com.weverse.sb.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {
	
	private String token;
    private Long userId;

    public JwtResponseDto(String token, Long userId) { 
        this.token = token; 
        this.userId = userId;
    }

}
