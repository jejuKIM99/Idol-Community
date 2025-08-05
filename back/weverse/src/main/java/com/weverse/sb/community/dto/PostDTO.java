package com.weverse.sb.community.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.weverse.sb.community.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	
	private Long userId;
    private Long artistId;
    private String content;
    private String image;
    private Integer likeCount;
    private LocalDateTime createdAt;
	
	private List<Post> postList;
	

}
