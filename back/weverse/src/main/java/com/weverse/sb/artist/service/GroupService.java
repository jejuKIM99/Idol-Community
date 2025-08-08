package com.weverse.sb.artist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.GroupDTO;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;


@Service
public class GroupService {

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	GroupRepository groupRepository;


	public GroupDTO findById(Long groupId) {
	    Optional<Group> group = groupRepository.findById(groupId);

	    if (group.isPresent()) {
	        Group g = group.get();
	        GroupDTO dto = GroupDTO.builder()
	                .groupId(g.getGroupId())
	                .groupName(g.getGroupName())
	                .groupProfileImage(g.getGroupProfileImage())
	                .groupLogo(g.getGroupLogo())
	                .build();
	        return dto;
	    } else {
	        return null;
	    }
	}

}
