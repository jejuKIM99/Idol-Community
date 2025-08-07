package com.weverse.sb.media.entity;

import java.time.LocalDateTime;

import com.weverse.sb.artist.entity.Artist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "streaming")
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "streaming_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_artist_id", nullable = false)
    private Artist owner;

    @ManyToOne
    @JoinColumn(name = "streamer_artist_id", nullable = false)
    private Artist streamer;

    @Column(name = "video_id", length = 255, nullable = false)
    private String videoId;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;
}
