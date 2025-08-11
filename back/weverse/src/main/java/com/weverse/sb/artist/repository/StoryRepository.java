package com.weverse.sb.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.artist.entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{
	List<Story> findByArtistArtistId(Long artistId);
}