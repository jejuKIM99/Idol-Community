package com.weverse.sb.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOARD_CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCategory {
    @Id
    @Column(name = "board_category_id")
    private Long boardCategoryId;

    @Column(name = "title")
    private String title;
}
