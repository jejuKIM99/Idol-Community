package com.weverse.sb.artist.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

	private Long id;
	private String name;
	private String image;
	private String logo;
	private List<Group> groupList;

}

