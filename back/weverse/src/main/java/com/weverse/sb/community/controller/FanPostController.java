package com.weverse.sb.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.service.ArtistService;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.service.PostService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class FanPostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	ArtistService artistService;
	
	
	// 팬 게시글 전체조회
		@GetMapping("/api/artistSNS/latestPost")
		public PostDTO latestPost(@RequestParam("groupId") Long groupId) {
			List<PostDTO> fanPostList = this.postService.getFanPostDTOList(groupId);
			PostDTO dto = PostDTO.builder().postList(fanPostList).build();

			return dto;
		}
	
	
	// 팬 게시글 전체조회
	@GetMapping("/api/artistSNS/fan")
	public PostDTO selectFanPost(@RequestParam("groupId") Long groupId) {
		List<PostDTO> fanPostList = this.postService.getFanPostDTOList(groupId);
		PostDTO dto = PostDTO.builder().postList(fanPostList).build();

		return dto;
	}
	
	// 팬 게시글 작성
	@PostMapping("/api/artistSNS/fan/fanInput")
	public ResponseEntity<String> inputFanPost(@RequestParam("artistID") Long artistID,
			@RequestParam("content") String content,
			@RequestParam("userId") Long userId) {
		try {
			postService.inputFanPost(artistID, content, userId);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		}
	}
	

}
