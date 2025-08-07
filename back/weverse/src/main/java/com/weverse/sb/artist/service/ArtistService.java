package com.weverse.sb.artist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.dto.GroupDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;


@Service
public class ArtistService {

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	GroupRepository groupRepository;

	// 아티스트 전부 조회
	public List<Artist> getList() {
		return artistRepository.findAll();
	}

	// 그룹명 전부 조회
	public List<Group> getGroupList() {
		return groupRepository.findAll();
	}

	// 특정 아티스트 한명 조회
	public ArtistDTO findById(Long artistId) {
	    Optional<Artist> artist = artistRepository.findById(artistId);

	    if (artist.isPresent()) {
	        Artist a = artist.get();
	        ArtistDTO dto = ArtistDTO.builder()
	                .id(a.getId())
	                .groupId(a.getGroup().getId())
	                .groupName(a.getGroup().getName())
	                .name(a.getName())
	                .stageName(a.getStageName())
	                .email(a.getEmail())
	                .profileImage(a.getProfileImage())
	                .build();
	        return dto;
	    } else {
	        return null;
	    }
	}


	// 특정 그룹 멤버 조회
	public List<Artist> getArtistDTOList(Long groupId) {
	    List<Artist> artists = artistRepository.findByGroupId(groupId);

	    List<Artist> artistList = artists.stream()
	            .map(a -> Artist.builder()
	                    .id(a.getId())
	                    .name(a.getName())
	                    .stageName(a.getStageName())
	                    .email(a.getEmail())
	                    .profileImage(a.getProfileImage())
	                    .build())
	            .collect(Collectors.toList());

	    return artistList;
	}


}
