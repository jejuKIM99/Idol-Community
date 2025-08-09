package com.weverse.sb.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.ArtistInfoResponseDTO;
import com.weverse.sb.artist.service.ArtistService;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.media.dto.StreamingDTO;
import com.weverse.sb.media.service.MediaService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class MediaController {
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	MediaService mediaService;
	
	@GetMapping("/api/artistSNS/live")
	public StreamingDTO selectStreaming(@RequestParam("groupId") Long groupId,
			@RequestParam("userId") Long userId) {
		List<StreamingDTO> streamList = this.mediaService.getStreamingDTOList(groupId, userId);
		StreamingDTO dto = StreamingDTO.builder().streamList(streamList).build();

		return dto;
	}


}
