package com.weverse.sb.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDto {
    private String nickname;   // 닉네임 변경
    private String name;       // 이름 변경
    private String password;   // 새 비밀번호
}
