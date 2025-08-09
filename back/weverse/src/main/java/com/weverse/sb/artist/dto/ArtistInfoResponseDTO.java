package com.weverse.sb.artist.dto;

import java.util.List;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistInfoResponseDTO {
    
	private Group group;
    private List<Artist> artists;
    
    
}