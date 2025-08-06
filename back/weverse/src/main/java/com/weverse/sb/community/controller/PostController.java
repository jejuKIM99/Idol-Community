package com.weverse.sb.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.service.PostService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PostController {
	
	@Autowired
	PostService postService;
	
	// 게시글 전체조회
	@GetMapping("api/artistSNS/home")
	public PostDTO selectPost() {
		List<PostDTO> postList = this.postService.getPostDTOList();
		PostDTO dto = PostDTO.builder().postList(postList).build();
		
		return dto;
	}

	// 게시글 작성
	@PostMapping("api/artistSNS/home/InputPost")
	public ResponseEntity<String> inputPost(@RequestParam("artistID") Long artistID,
			@RequestParam("content") String content, @RequestParam("image") String image) {
	    try {
	        postService.inputPost(artistID, content);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	
	// 특정 아티스트 게시글 전체조회
	@GetMapping("api/artistSNS/home/artist")
	public PostDTO filterPost(@RequestParam("artistID") Long id) {
		List<PostDTO> postList = this.postService.getFilterPostList(id);
		PostDTO dto = PostDTO.builder().postList(postList).build();
		
		return dto;
	} 
	
	// 게시글 좋아요 하기
	@PostMapping("api/artistSNS/home/like")
	@Transactional
    public ResponseEntity<String> likePost(@RequestParam("postId") Long postId,
    		@RequestParam("userId") Long userId) {
        
        try {
        	postService.inputPostLike(postId, userId);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
        
    }
	
	// 게시글 좋아요 취소
	@PostMapping("api/artistSNS/home/dislike")
	@Transactional
    public ResponseEntity<String> unlikePost(@RequestParam Long postId, @RequestParam Long userId) {
        try {
            postService.deletePostLike(postId, userId);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("fail: " + e.getMessage());
        }
    }
	
	

}
