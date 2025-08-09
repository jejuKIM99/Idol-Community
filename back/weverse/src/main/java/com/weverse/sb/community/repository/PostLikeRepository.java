package com.weverse.sb.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.community.entity.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike	, Long> {
	
	boolean existsByUserUserIdAndPostId(Long userId, Long postId);
	
    void deleteByUserUserIdAndPostId(Long userId, Long postId);
    
    int countByPostId(Long postId);

}
