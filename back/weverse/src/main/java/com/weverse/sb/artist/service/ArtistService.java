package com.weverse.sb.artist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
<<<<<<< HEAD
import com.weverse.sb.artist.repository.GroupRepository;
=======
import com.weverse.sb.artist.repository.ArtistGroupMapRepository;
>>>>>>> 2e010c7a5fb945b3bd108c29261d949027950853
import com.weverse.sb.artist.repository.ArtistRepository;


@Service
public class ArtistService {
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
<<<<<<< HEAD
	GroupRepository artistGroupMapRepository;
=======
	ArtistGroupMapRepository artistGroupMapRepository;
>>>>>>> 2e010c7a5fb945b3bd108c29261d949027950853
	
	public List<Artist> getList() {
        return artistRepository.findAll();
    }
	
	public List<Group> getGroupList() {
		return artistGroupMapRepository.findAll();
	}

}
