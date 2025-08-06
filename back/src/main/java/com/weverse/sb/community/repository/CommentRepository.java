package com.weverse.sb.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.community.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
}