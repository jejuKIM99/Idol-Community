package com.weverse.sb.media.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StreamingDTO {
	
    private Long streamingId;
    private Long owner;
    private Long streamer;
    private String title;
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime scheduledAt;
    private Long groupId;
    private String groupName;
    
    private List<StreamingDTO> streamList;

}
