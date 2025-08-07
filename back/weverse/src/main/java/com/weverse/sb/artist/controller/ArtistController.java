package com.weverse.sb.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.dto.ArtistInfoResponseDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.service.ArtistService;
import com.weverse.sb.media.entity.Streaming;
import com.weverse.sb.media.entity.UploadedVideo;
import com.weverse.sb.media.repository.StreamingRepository;




@RestController
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@Autowired
	StreamingRepository streamingRepository;
	
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
	// 그룹 조회 함수
	@GetMapping("/api/artist/group")
	public ArtistInfoResponseDTO getArtistInfo(@RequestParam("groupId") Long groupId) {
        ArtistInfoResponseDTO dto = artistService.getArtistInfoByGroupId(groupId);
        return dto;
    }
	
	// 멤버 조회 함수
	@GetMapping("/api/artistinfo/groupMembers")
	public List<Artist> getGroupMembers(@RequestParam("groupId") Long groupId) {
	    List<Artist> members = artistService.getArtistsByGroupId(groupId);
	    return members;
	}
	// 라이브 조회 함수
	@GetMapping("/api/artistinfo/artistLive")
	public ResponseEntity<List<Streaming>> getStreamingsByGroup(@RequestParam("groupId") Long groupId) {
	    List<Streaming> list = artistService.getStreamingsByGroup(groupId);
	    return ResponseEntity.ok(list);
	}
	// 미디어 조회 함수
	@GetMapping("/api/artistinfo/artistMedia")
	public ResponseEntity<List<UploadedVideo>> getMediaByGroupId(@RequestParam("groupId") Long groupId) {
	    List<UploadedVideo> videos = artistService.getVideosByGroupId(groupId);
	    return ResponseEntity.ok(videos);
	}
}
