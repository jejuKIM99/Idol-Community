package com.weverse.sb.artist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
