package com.weverse.sb.shopArtistTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.product.service.ProductService;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;
import com.weverse.sb.user.service.UserService;

@SpringBootTest
class ShopArtistInsertTests {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private GroupRepository artistGroupMapRepository;
	
	/*
	@Test
    void insertArtistGroup() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            ArtistGroupMap groupMap = ArtistGroupMap.builder()
                    .groupName("Group" + i)
                    .groupProfile("This is the profile of Group" + i)
                    .groupLogo("group" + i + "_logo.png")
                    .build();

            this.artistGroupMapRepository.save(groupMap);
        });
        
        System.out.println(">> 3개 그룹 값 삽입 성공");
    }
    */
	
	/*
	@Test
	void insertArtist() {
		
		ArtistGroupMap group = artistGroupMapRepository.findById(1L).orElseThrow();
		
		IntStream.rangeClosed(1, 5).forEach(i -> {
            Artist artist = Artist.builder()
                    .group(group)
                    .stageName("Artist " + i)
                    .email("artist" + i + "@mail.com")
                    .password("pw" + i)
                    .profileImage("artist" + i + "_profile.png")
                    .snsUrl("https://sns.com/artist" + i)
                    .birthday(LocalDateTime.of(1990 + i, 1, i, 0, 0))
                    .statusMessage("상태 메시지 " + i)
                    .dmNickname("DMNick" + i)
                    .build();

            artistRepository.save(artist);
        });
		
		System.out.println(">> 5명 아티스트 삽입 성공");
          
    }
    */
    

	
	
	@Test
	void checkArtistList() {
		
		List<ShopArtistDTO> dto = this.productService.searchArtistList();
		
		System.out.println(">> test 아티스트 리스트: " + dto);
		
	}
	
	
    
	
}
