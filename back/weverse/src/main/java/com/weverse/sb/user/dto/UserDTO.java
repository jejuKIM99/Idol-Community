package com.weverse.sb.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    
    @JsonProperty("jellyBalance")
    private Integer jellyBalance;
    
    @JsonProperty("cashBalance")
    private BigDecimal cashBalance;
    
    private Boolean isEmailVerified;
    private Boolean isSmsVerified;
    private LocalDateTime createdAt; 

    // 명시적으로 getter 구현
    public Integer getJellyBalance() {
        return this.jellyBalance;
    }

    public BigDecimal getCashBalance() {
        return this.cashBalance;
    }

}
