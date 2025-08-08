package com.weverse.sb.artist.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StoryCommentDTO {
    private String comment;
    private String userNickname;
    private Long likeCount;
    private LocalDateTime createdDate;
}
