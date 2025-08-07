package com.weverse.sb.media.entity;

import java.time.LocalDateTime;

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
@Table(name = "chat_archive")
public class ChatArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "streaming_id", nullable = false)
    private Streaming streaming;

    @Column(name = "archive_url", length = 2083, nullable = false)
    private String archiveUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}