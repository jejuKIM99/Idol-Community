package com.weverse.sb.media.entity;

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
@Table(name = "SYNC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sync {
    @Id
    @Column(name = "sync_id")
    private Long syncId;

    @ManyToOne
    @JoinColumn(name = "streaming_id")
    private Streaming streaming;

    @Column(name = "timeline")
    private String timeline;

    @Column(name = "play_status")
    private String playStatus;
}
