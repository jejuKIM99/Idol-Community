package com.weverse.sb.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.board.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	
}
