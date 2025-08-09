package com.weverse.sb.community.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.weverse.sb.community.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long userId;
    private Long groupId;
    private Long artistId;
    private Long postId;
    private String content;
    private LocalDateTime createdAt;

     private List<Comment> commentList;
}