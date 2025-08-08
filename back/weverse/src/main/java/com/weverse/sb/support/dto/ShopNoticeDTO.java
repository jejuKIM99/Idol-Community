package com.weverse.sb.support.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopNoticeDTO {

    private Long noticeId;             // 공지사항 ID
    private String title;              // 제목
    private String content;            // 내용
    private LocalDateTime createdAt;   // 작성일시
}

