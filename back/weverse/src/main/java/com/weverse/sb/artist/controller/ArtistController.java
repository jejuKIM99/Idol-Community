package com.weverse.sb.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.ArtistGroupMap;
import com.weverse.sb.artist.service.ArtistService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@GetMapping("/api/main/artist")
	public ArtistDTO selectArtist() {
		List<Artist> artistList = this.artistService.getList();
		List<ArtistGroupMap> artistGroupList = this.artistService.getGroupList();
		ArtistDTO dto = ArtistDTO.builder()
				.artistList(artistList)
				.groupList(artistGroupList)
				.build();
		
		return dto;

	}
	
	

}