package com.weverse.sb.artist.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "story")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "story_id")
    private Long storyId; // 아티스트 ID
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "story_img", length = 255)
    private String storyImg; // 스토리 이미지

    @Column(name = "group_id")
    private Long groupId; // 관계 ID (PK)

    @Column(name = "create_at",
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt; // 스토리 생성일자
    
    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    private List<StoryComment> comments;
}
