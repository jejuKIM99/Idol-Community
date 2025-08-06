package com.weverse.sb.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.service.ArtistService;




@RestController
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@GetMapping("/api/main/artist")
	public ArtistDTO selectArtist() {
		List<Artist> artistList = this.artistService.getList();
		List<Group> artistGroupList = this.artistService.getGroupList();
		ArtistDTO dto = ArtistDTO.builder()
				.artistList(artistList)
				.groupList(artistGroupList)
				.build();
		return dto;
	}
}
