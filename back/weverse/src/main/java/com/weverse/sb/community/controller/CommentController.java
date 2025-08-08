package com.weverse.sb.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.community.dto.CommentDTO;
import com.weverse.sb.community.entity.Comment;
import com.weverse.sb.community.service.CommentService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("api/artistSNS/home/comment")
	public CommentDTO selectPostCommend(@RequestParam("postId") Long postId) {
		List<Comment> commentList = this.commentService.getCommentList(postId);
		CommentDTO dto = CommentDTO.builder().commentList(commentList).build();
		
		return dto;
	}
	
	
	@PostMapping("api/artistSNS/home/InputComment")
	public ResponseEntity<String> inputComment(@RequestParam("postId") Long postId, 
			@RequestParam("content") String content,
			@RequestParam("userId") Long userId) {
	    try {
	        commentService.inputComment(postId, content, userId);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	

}
