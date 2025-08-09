package com.weverse.sb.artist.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.weverse.sb.user.entity.User;

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
@Table(name = "story_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storycomment_id")
    private Long storyCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "likeCount")
    private Long likeCount;
    
}
