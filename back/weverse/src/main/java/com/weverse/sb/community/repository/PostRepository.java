package com.weverse.sb.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weverse.sb.community.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByArtist_Id(Long artistId);

	List<Post> findByArtist_IdAndAuthorType(Long artistId, String authorType);

	List<Post> findByAuthorType(String string);

	List<Post> findByGroup_IdAndAuthorType(Long groupId, String authorType);
	
}
