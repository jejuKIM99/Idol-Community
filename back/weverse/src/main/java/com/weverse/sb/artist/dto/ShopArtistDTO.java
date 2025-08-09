package com.weverse.sb.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopArtistDTO {

    private Long artistId;           // 아티스트/그룹 ID
    private Long groupId;            // 그룹 ID
    private String stageName;        // 활동명/그룹명
    private String email;            // 이메일
    private String password;         // 비밀번호
    private String profileImage;     // 프로필 사진 URL
    private String snsUrl;           // SNS URL
    private LocalDateTime birthday;  // 생일 (가입일 기준)
    private String statusMessage;    // 상태 메시지
    private String dmNickname;       // DM용 닉네임
    
    
}

