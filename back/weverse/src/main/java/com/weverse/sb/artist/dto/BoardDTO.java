package com.weverse.sb.artist.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.weverse.sb.artist.entity.Board;
import com.weverse.sb.artist.entity.BoardCategory;

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
public class BoardDTO {
	
	private Long id;
	
	private Long categoryId;

	private String catagoryName;
    
    private String groupName;

    private String title;

    private String content;

    private String thumbnailImage;

    private LocalDateTime createdAt;
    
    private List<BoardDTO> boardList;

}
