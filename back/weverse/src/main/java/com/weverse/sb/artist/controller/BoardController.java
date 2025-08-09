package com.weverse.sb.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.BoardDTO;
import com.weverse.sb.artist.entity.Board;
import com.weverse.sb.artist.service.ArtistService;
import com.weverse.sb.artist.service.BoardService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BoardController {

	@Autowired
	ArtistService artistService;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("api/artistSNS/home/notice")
	public BoardDTO selectNotice(@RequestParam("groupId") Long groupId) {
		List<BoardDTO> boardList = this.boardService.getList(groupId);
		BoardDTO dto = BoardDTO.builder()
				.boardList(boardList)
				.build();
		
		return dto;
	}
	
	// 게시글 작성
	@PostMapping("/api/artistSNS/home/notice")
	public ResponseEntity<String> inputNotice(@RequestParam("groupId") Long groupId,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("board_category_id") Long boardCatagoryId) {
		try {
			boardService.inputNotice(boardCatagoryId, groupId, title, content);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		}
	}
}