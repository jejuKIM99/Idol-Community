package com.weverse.sb.artist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.artist.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Long>{

	List<Story> findByArtistArtistId(Long artistId); 
	
}
