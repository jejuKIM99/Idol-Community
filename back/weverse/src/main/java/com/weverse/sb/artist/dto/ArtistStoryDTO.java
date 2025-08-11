package com.weverse.sb.artist.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ArtistStoryDTO {
    private String storyImg;
    private LocalDateTime storyCreatedAt;
    private String artistName;
    private Long groupId;
    private List<StoryCommentDTO> comments;
}
