package com.weverse.sb.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.artist.entity.Story;
import com.weverse.sb.artist.entity.StoryComment;

public interface StoryCommentRepository extends JpaRepository<StoryComment, Long>{

	// 특정 스토리에 달린 모든 댓글 조회
    List<StoryComment> findByStory(Story story);
	
}
