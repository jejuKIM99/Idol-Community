package com.weverse.sb.media.dto;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.media.entity.Streaming;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreamingDto {
    private Long streamingId;
    private Artist owner;
    private Artist streamer;
    private String videoId;
    private String title;
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime scheduledAt;
    private Group group;

    public static StreamingDto fromEntity(Streaming streaming) {
        return StreamingDto.builder()
            .streamingId(streaming.getStreamingId())
            .owner(streaming.getOwner())
            .streamer(streaming.getStreamer())
            .videoId(streaming.getVideoId())
            .title(streaming.getTitle())
            .thumbnail(streaming.getThumbnail())
            .createdAt(streaming.getCreatedAt())
            .scheduledAt(streaming.getScheduledAt())
            .group(streaming.getGroup())
            .build();
    }
}