package com.weverse.sb.entity.chatting;

import java.time.LocalDateTime;

import com.weverse.sb.entity.user.Artist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STREAMING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Streaming {
    @Id
    @Column(name = "streaming_id")
    private Long streamingId;

    @ManyToOne
    @JoinColumn(name = "owner_artist_id")
    private Artist ownerArtist;

    @ManyToOne
    @JoinColumn(name = "streamer_artist_id")
    private Artist streamerArtist;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;
}
