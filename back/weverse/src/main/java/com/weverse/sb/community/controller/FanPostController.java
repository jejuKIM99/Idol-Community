package com.weverse.sb.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.BoardDTO;
import com.weverse.sb.artist.dto.GroupDTO;
import com.weverse.sb.artist.entity.Board;
import com.weverse.sb.artist.service.ArtistService;
import com.weverse.sb.artist.service.BoardService;
import com.weverse.sb.artist.service.GroupService;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.service.PostService;
import com.weverse.sb.user.dto.UserDTO;
import com.weverse.sb.user.service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class FanPostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BoardService boardService;
	
	
	// 아티스트 최신 게시글 조회
		@GetMapping("/api/artistSNS/latestPost")
		public PostDTO latestPost(@RequestParam("userId") Long userId, @RequestParam("groupId") Long groupId ) {
			List<PostDTO> postList = this.postService.getPostDTOList(userId, groupId);
			PostDTO dto = PostDTO.builder().postList(postList).build();

			return dto;
		}
	
	
	// 팬 게시글 전체조회
	@GetMapping("/api/artistSNS/fan")
	public PostDTO selectFanPost(@RequestParam("groupId") Long groupId) {
		List<PostDTO> fanPostList = this.postService.getFanPostDTOList(groupId, "user");
		PostDTO dto = PostDTO.builder().postList(fanPostList).build();

		return dto;
	}
	
	// 팬 게시글 전체조회
		@GetMapping("/api/artistSNS/artistPost")
		public PostDTO selectArtistPost(@RequestParam("groupId") Long groupId) {
			List<PostDTO> fanPostList = this.postService.getFanPostDTOList(groupId, "artist");
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
	
	// 그룹 정보 조회
	@GetMapping("/api/artistSNS/fan/groupInfo")
	public GroupDTO groupInfo(@RequestParam("groupId") Long groupId) {
		GroupDTO dto = this.groupService.findById(groupId);

		return dto;
	}
	
	
	// 내 정보 조회
	@GetMapping("/api/artistSNS/fan/myProfile")
	public UserDTO userInfo(@RequestParam("userId") Long userId) {
		UserDTO dto = this.userService.getUserInfo(userId);

		return dto;
	}
	
	// 공지사항 가져오기
	@GetMapping("/api/artistSNS/fan/notice")
	public BoardDTO noticeInfo(@RequestParam("groupId") Long groupId) {
		List<BoardDTO> boardList = this.boardService.getList(groupId);
		BoardDTO dto = BoardDTO.builder().boardList(boardList).build();

		return dto;
	}

}
