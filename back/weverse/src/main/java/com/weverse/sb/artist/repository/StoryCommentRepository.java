package com.weverse.sb.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.artist.entity.Story;
import com.weverse.sb.artist.entity.StoryComment;

@Repository
public interface StoryCommentRepository extends JpaRepository<StoryComment, Long>{
	List<StoryComment> findByStory(Story story);
}

