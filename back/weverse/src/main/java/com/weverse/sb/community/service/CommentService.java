package com.weverse.sb.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.community.dto.CommentDTO;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Comment;
import com.weverse.sb.community.entity.Post;

@Service
public interface CommentService {

	List<Comment> getCommentList(Long postId);

	void inputComment(CommentDTO commentDTO);
	


}
