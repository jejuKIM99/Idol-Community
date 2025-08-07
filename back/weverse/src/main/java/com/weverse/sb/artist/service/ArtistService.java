package com.weverse.sb.artist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ArtistDTO;
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

	public List<Artist> getList() {
		return artistRepository.findAll();
	}

	public List<Group> getGroupList() {
		return groupRepository.findAll();
	}

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

}
