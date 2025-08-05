package com.weverse.sb.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Post;

@Service
public interface PostService {

	List<Post> getList();

	void inputPost(PostDTO postDTO);

	List<Post> getFilterPostList(Long id);



}
