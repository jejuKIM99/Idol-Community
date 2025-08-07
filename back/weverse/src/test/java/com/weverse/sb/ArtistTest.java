package com.weverse.sb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.controller.ArtistController;
import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistGroupMapRepository;
import com.weverse.sb.artist.repository.ArtistRepository;


@SpringBootTest
class ArtistTest {
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	ArtistGroupMapRepository arMapRepository;
	
	@Autowired
	ArtistController artistController;
	

	   @Test
	   void insertArtist() {
		   
			/*
			 * Group group = Group.builder() .groupId(1l) .groupName("NewJeans")
			 * .groupProfileImage("/images/NewJeans_artistpedia_11.png")
			 * .groupLogo("/images/NewJeans_artistpedia_01.png") .build();
			 * arMapRepository.save(group);
			 */
		   
		   Group group = Group.builder() .groupId(1l) .groupName("NewJeans")
					 .groupProfileImage("/images/NewJeans_artistpedia_11.png")
					 .groupLogo("/images/NewJeans_artistpedia_01.png") .build();
		   
		   arMapRepository.save(group);

		   Artist artist1 = Artist.builder()
	        		 .name("민지")
	        		 .group(group)
	        		 .profileImage("/images/NewJeans_artistpedia_02.png")
	        		 .build();
	    	 
	    	 Artist artist2 = Artist.builder()
	        		 .name("하니")
	        		 .group(group)
	        		 .profileImage("/images/NewJeans_artistpedia_03.png")
	        		 .build();
	    	 
	    	 
	    	 Artist artist3 = Artist.builder()
	        		 .name("다니엘")
	        		 .group(group)
	        		 .profileImage("/images/NewJeans_artistpedia_04.png")
	        		 .build();
	    	 
	    	 Artist artist4 = Artist.builder()
	        		 .name("해린")
	        		 .group(group)
	        		 .profileImage("/images/NewJeans_artistpedia_05.png")
	        		 .build();
	    	 
	    	 Artist artist5 = Artist.builder()
	        		 .name("혜인")
	        		 .group(group)
	        		 .profileImage("/images/NewJeans_artistpedia_06.png")
	        		 .build();


	         this.artistRepository.save(artist1);
	         this.artistRepository.save(artist2);
	         this.artistRepository.save(artist3);
	         this.artistRepository.save(artist4);
	         this.artistRepository.save(artist5);  
	         

	   }
	   
	   @Test
	    public void testAllArtistsAndGroups() {
	        ArtistDTO dto = artistController.selectArtist();

	        System.out.println("=== 전체 아티스트 리스트 ===");
	        dto.getArtistList().forEach(artist -> {
	            System.out.println("ID: " + artist.getArtistId() + ", 이름: " + artist.getName());
	            if (artist.getArtistId() != null) {
	                System.out.println("  그룹명: " + artist.getGroup().getGroupName());
	            }
	        });

	        System.out.println("=== 전체 그룹 리스트 ===");
	        dto.getGroupList().forEach(group -> {
	            System.out.println("그룹ID: " + group.getGroupId() + ", 그룹명: " + group.getGroupName());
	        });

	   }
}
