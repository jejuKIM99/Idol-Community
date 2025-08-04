package com.weverse.sb.entity.chatting;

import java.time.LocalDateTime;

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
@Table(name = "DM_MESSAGE_ARCHIVE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DmMessageArchive {
    @Id
    @Column(name = "archive_id")
    private Long archiveId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private DmMessage message;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;
}
