package com.weverse.sb.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String country;
    private String profileImage;
    private Integer jellyBalance;
    private Integer cashBalance;
    private Boolean isEmailVerified;
    private Boolean isSmsVerified;
    private LocalDateTime createdAt; 

}
