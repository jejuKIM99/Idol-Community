package com.weverse.sb.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Post;
import com.weverse.sb.community.service.PostService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PostController {
	
	@Autowired
	PostService postService;
	
	
	@GetMapping("api/artistSNS/home")
	public PostDTO selectPost() {
		List<Post> postList = this.postService.getList();
		PostDTO dto = PostDTO.builder().postList(postList).build();
		
		return dto;
	}


	@PostMapping("api/artistSNS/post/inputPost")
	public ResponseEntity<String> inputPost(@RequestBody PostDTO postDTO) {
	    try {
	        postService.inputPost(postDTO);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	
	@GetMapping("api/artistSNS/post/{artistID}")
	public PostDTO filterPost(@PathVariable("artistID") Long id) {
		List<Post> postList = this.postService.getFilterPostList(id);
		PostDTO dto = PostDTO.builder().postList(postList).build();
		
		return dto;
	} 
	

}
