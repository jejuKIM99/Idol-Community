package com.weverse.sb.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDto {
	
	@Size(min = 2, max = 20, message = "닉네임은 2~20자여야 합니다.")
    @Pattern(regexp = "^[A-Za-z0-9가-힣_]*$", message = "닉네임은 한글·영문·숫자·_만 사용 가능합니다.")
    private String nickname;   // 닉네임 변경
	
	@Size(min = 2, max = 30, message = "이름은 2~30자여야 합니다.")
    private String name;       // 이름 변경
    
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private String password;   // 새 비밀번호
}
