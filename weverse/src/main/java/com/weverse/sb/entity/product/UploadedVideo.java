package com.weverse.sb.entity.product;

import java.math.BigDecimal;
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
@Table(name = "UPLOADED_VIDEO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedVideo {
    @Id
    @Column(name = "video_id")
    private Long videoId;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;
}