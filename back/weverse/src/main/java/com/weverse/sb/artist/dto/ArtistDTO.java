<<<<<<< HEAD
package com.weverse.sb.artist.dto;

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
	
	private List<Artist> artistList;
	private List<Group> groupList;
	

=======
package com.weverse.sb.artist.dto;

import java.util.List;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.ArtistGroupMap;

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
	
	private List<Artist> artistList;
	private List<ArtistGroupMap> groupList;
	

>>>>>>> 8b6fb4b9b280173a45025a868ae4dc674cf7c9de
}