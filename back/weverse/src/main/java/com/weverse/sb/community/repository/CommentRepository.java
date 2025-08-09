package com.weverse.sb.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.community.entity.Comment;
import com.weverse.sb.community.entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	// 특정 포스트에 달린 댓글 목록 가져오기
	List<Comment> findByPostId(Long postId);
	
	// 포스트에 달린 댓글 개수 세기
	int countByPostId(Long postId);
	
}