package com.weverse.sb.entity.chatting;

import java.time.LocalDateTime;

import com.weverse.sb.entity.user.Artist;
import com.weverse.sb.entity.user.User;

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
@Table(name = "DM_REPLY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DmReply {
    @Id
    @Column(name = "reply_id")
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "content")
    private String content;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}