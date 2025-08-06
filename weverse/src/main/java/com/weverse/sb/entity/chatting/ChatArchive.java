package com.weverse.sb.entity.chatting;

import java.time.LocalDateTime;

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
@Table(name = "CHAT_ARCHIVE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatArchive {
    @Id
    @Column(name = "archive_id")
    private Long archiveId;

    @ManyToOne
    @JoinColumn(name = "streaming_id")
    private Streaming streaming;

    @Column(name = "archive_url")
    private String archiveUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
