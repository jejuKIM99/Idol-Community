package com.weverse.sb.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.community.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByArtist_ArtistId(Long artistId);

	List<Post> findByArtist_ArtistIdAndAuthorType(Long artistId, String authorType);

	List<Post> findByAuthorType(String string);

	List<Post> findByGroup_GroupIdAndAuthorType(Long groupId, String authorType);
	
}
