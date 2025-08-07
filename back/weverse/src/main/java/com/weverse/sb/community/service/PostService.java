package com.weverse.sb.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.community.dto.PostDTO;

@Service
public interface PostService {
	
	List<PostDTO> getPostDTOList();

	void inputPost(Long artistID, String content);

	List<PostDTO> getFilterPostList(Long id);
	
	void inputPostLike(Long postId, Long userId);

    void deletePostLike(Long postId, Long userId);
	
	

	



}
