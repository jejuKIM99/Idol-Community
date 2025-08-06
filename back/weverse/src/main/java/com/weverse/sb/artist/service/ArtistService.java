<<<<<<< HEAD
package com.weverse.sb.artist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.artist.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	GroupRepository artistGroupMapRepository;
	
	public List<Artist> getList() {
        return artistRepository.findAll();
    }
	
	public List<Group> getGroupList() {
		return artistGroupMapRepository.findAll();
	}

}
=======
package com.weverse.sb.artist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.ArtistGroupMap;
import com.weverse.sb.artist.repository.ArtistGroupMapRepository;
import com.weverse.sb.artist.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	ArtistGroupMapRepository artistGroupMapRepository;
	
	public List<Artist> getList() {
        return artistRepository.findAll();
    }
	
	public List<ArtistGroupMap> getGroupList() {
		return artistGroupMapRepository.findAll();
	}

}
>>>>>>> 8b6fb4b9b280173a45025a868ae4dc674cf7c9de
