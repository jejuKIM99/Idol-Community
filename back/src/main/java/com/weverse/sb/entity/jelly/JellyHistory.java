package com.weverse.sb.entity.jelly;

import java.time.LocalDateTime;

import com.weverse.sb.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "jelly_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JellyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jelly_history_id")
    private Long jellyHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "change_amount", nullable = false)
    private Integer changeAmount;

    @Column(name = "balance_after", nullable = false)
    private Integer balanceAfter;

    @Column(name = "change_type", nullable = false, length = 50)
    private String changeType;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}

