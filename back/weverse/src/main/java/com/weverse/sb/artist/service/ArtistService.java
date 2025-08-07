package com.weverse.sb.artist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ArtistInfoResponseDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistGroupMapRepository;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.media.entity.Streaming;
import com.weverse.sb.media.entity.UploadedVideo;
import com.weverse.sb.media.repository.StreamingRepository;
import com.weverse.sb.media.repository.UploadedVideoRepository;
import com.weverse.sb.product.repository.ProductRepository;

@Service
public class ArtistService {
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	ArtistGroupMapRepository artistGroupMapRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private StreamingRepository streamingRepository;
	
	@Autowired
	UploadedVideoRepository uploadedVideoRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Artist> getList() {
        return artistRepository.findAll();
    }
	
	// 그룹 조회 서비스
	public List<Group> getGroupList() {
		return artistGroupMapRepository.findAll();
	}
	public ArtistInfoResponseDTO getArtistInfoByGroupId(Long groupId) {
	    // 그룹 조회
		Group group = groupRepository.findById(groupId)
			    .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));


	    // 아티스트 조회
	    List<Artist> artists = artistRepository.findByGroup_GroupId(groupId);

	    ArtistInfoResponseDTO dto = ArtistInfoResponseDTO.builder().group(group).artists(artists).build();

	    return dto;
	}

	//멤버조회 서비스
	public List<Artist> getArtistsByGroupId(Long groupId) {
	    return artistRepository.findByGroup_GroupId(groupId);
	}
	
	// 라이브 조회 서비스
	public List<Streaming> getStreamingsByGroup(Long groupId) {
	    return streamingRepository.findByGroup_GroupId(groupId);
	}

	// 미디어 조회 서비스
	public List<UploadedVideo> getVideosByGroupId(Long groupId) {
	    return uploadedVideoRepository.findByGroup_GroupId(groupId);
	}
}
