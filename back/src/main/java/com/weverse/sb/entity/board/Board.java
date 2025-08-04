package com.weverse.sb.entity.board;

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
@Table(name = "BOARD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "board_category_id")
    private BoardCategory boardCategory;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
