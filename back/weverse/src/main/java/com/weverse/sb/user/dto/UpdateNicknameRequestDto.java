package com.weverse.sb.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateNicknameRequestDto {
	
	@NotNull(message = "userId는 필수입니다.")
    private Long userId;
    
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하로 입력해주세요.")
    private String nickname;
}

