package com.weverse.sb.media.entity;

import java.time.LocalDateTime;

import com.weverse.sb.user.entity.User;

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
@Table(name = "LIVE_CHAT_MESSAGE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiveChatMessage {
    @Id
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "streaming_id")
    private Streaming streaming;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "content")
    private String content;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}