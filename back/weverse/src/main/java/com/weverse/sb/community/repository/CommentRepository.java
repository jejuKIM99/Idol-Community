package com.weverse.sb.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.community.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	// 포스트에 달린 댓글 개수 세기
	int countByPostId(Long postId);

	List<Comment> findByGroup_GroupIdAndAuthorType(Long groupId, String authorType);
	
}