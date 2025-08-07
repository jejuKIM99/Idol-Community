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
public class ArtistDTO {

	private Long id;
	
	private Long groupId;
	
	private String groupName;
	
	private String name;

	private String stageName;

	private String email;

	private String password;

	private String profileImage;

	private String snsUrl;

	private LocalDateTime birthday;

	private String statusMessage;

	private String dmNickname;

	private List<Artist> artistList;
	private List<Group> groupList;

}
