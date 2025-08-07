package com.weverse.sb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.weverse.sb.artist.controller.ArtistController;
import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.media.entity.Streaming;

@SpringBootTest
class ArtistTest {

   @Autowired
   ArtistRepository artistRepository;

   @Autowired
   GroupRepository arMapRepository;

   @Autowired
   ArtistController artistController;

   

   @Test 
   void insertNJZ() { 
      Group group = Group.builder().groupName("NewJeans")
   .groupProfileImage("images/NewJeans/NewJeans_profile/NewJeans_group.png")
     .groupLogo("/images/NewJeans/NewJeans_profile/NewJeans_logo.png") .build();
     arMapRepository.save(group);
     
     String[] memberNames = {"Minji", "Hanny", "Daniel", "Haerin", "Hyein"};
     
     for (int i = 0; i < memberNames.length; i++) { 
    	 String profileImage = String.format("/images/NewJeans/NewJeans_profile/NewJeans_%s.png", memberNames[i]); 
     Artist artist = Artist.builder() .group(group).stageName(memberNames[i])
     .email("njz@gmail.com") .password("NewJeans") .profileImage(profileImage)
     .build(); artistRepository.save(artist); } }
    
   
   
   @Test
   void insertLESSERAFIM() {
      Group group = Group.builder()
               .groupName("LESSERAFIM")
               .groupProfileImage("images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_group.png")
               .groupLogo("/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_logo.png")
               .build();
       arMapRepository.save(group);

       String[] memberNames = {"Sakura", "KimChaewon", "HuhYunjin", "Kazuha", "HongEunchae"};

       for (int i = 0; i < memberNames.length; i++) {
           String profileImage = String.format("/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_%s.png", memberNames[i]);
           Artist artist = Artist.builder()
                   .stageName(memberNames[i])
                   .email("lesserafim@gmail.com")
                   .password("LESSERAFIM")
                   .profileImage(profileImage)
                   .group(group)
                   .build();
           artistRepository.save(artist);
       }
   }
   

   
   @Test
   void insertNCTWISH() {
      Group group = Group.builder()
    		   .groupName("NCTWISH")
               .groupProfileImage("images/NCT_WISH/NCT_WISH_profile/NCT_WISH_group.png")
               .groupLogo("/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_logo.png")
               .build();
       arMapRepository.save(group);

       String[] memberNames = {"Sion", "Riku", "Yushi", "Jaehee", "Ryo", "Sakuya"};

       for (int i = 0; i < memberNames.length; i++) {
           String profileImage = String.format("/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_%s.png", memberNames[i]);
           Artist artist = Artist.builder()
                   .stageName(memberNames[i])
                   .email("nctwish@gmail.com")
                   .password("NCTWISH")
                   .profileImage(profileImage)
                   .group(group)
                   .build();
           artistRepository.save(artist);
       }
   }

   
   @Test
   void insertRIIZE() {
      Group group = Group.builder()
               .groupName("RIIZE")
               .groupProfileImage("images/RIIZE/RIIZE_profile/RIIZE_group.png")
               .groupLogo("/images/RIIZE/RIIZE_profile/RIIZE_logo.png")
               .build();
       arMapRepository.save(group);

       String[] memberNames = {"Shotaro", "Eunseok", "Sungchan", "Wonbin", "Sohee", "Anton"};

       for (int i = 0; i < memberNames.length; i++) {
           String profileImage = String.format("/images/RIIZE/RIIZE_profile/RIIZE_%s.png", memberNames[i]);
           Artist artist = Artist.builder()
                   .stageName(memberNames[i])
                   .email("riize@gmail.com")
                   .password("RIIZE")
                   .profileImage(profileImage)
                   .group(group)
                   .build();
           artistRepository.save(artist);
       }
   }

   
   @Test
   void insertSTAYC() {
      Group group = Group.builder()
               .groupName("STAYC")
               .groupProfileImage("images/STAYC/STAYC_profile/STAYC_group.png")
               .groupLogo("/images/STAYC/STAYC_profile/STAYC_logo.png")
               .build();
       arMapRepository.save(group);

       String[] memberNames = {"Sumin", "Sieun", "Isa", "Seeun", "Yoon", "Jaei"};

       for (int i = 0; i < memberNames.length; i++) {
           String profileImage = String.format("/images/STAYC/STAYC_profile/STAYC_%s.png", memberNames[i]);
           Artist artist = Artist.builder()
                   .stageName(memberNames[i])
                   .email("stayc@gmail.com")
                   .password("STAYC")
                   .profileImage(profileImage)
                   .group(group)
                   .build();
           artistRepository.save(artist);
       }
   }

   @Test
   void insertTWS() {
      Group group = Group.builder()
               .groupName("TWS")
               .groupProfileImage("images/TWS/TWS_profile/TWS_group.png")
               .groupLogo("/images/TWS/TWS_profile/TWS_logo.png")
               .build();
       arMapRepository.save(group);

       String[] memberNames = {"Shinyu", "Dohoon", "Youngjae", "Hanjin", "Jihoon", "Gyeongmin"};

       for (int i = 0; i < memberNames.length; i++) {
           String profileImage = String.format("/images/TWS/TWS_profile/TWS_%s.png", memberNames[i]);
           Artist artist = Artist.builder()
                   .stageName(memberNames[i])
                   .email("tws@gmail.com")
                   .password("TWS")
                   .profileImage(profileImage)
                   .group(group)
                   .build();
           artistRepository.save(artist);
       }
   }

   @Test
   void insertBABYMONSTER() {
      Group group = Group.builder()
               .groupName("BABYMONSTER")
               .groupProfileImage("images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_group.png")
               .groupLogo("/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_logo.png")
               .build();
       arMapRepository.save(group);

       String[] memberNames = {"ruka", "pharita", "asa", "ahyeon", "rami", "rora", "chiquita"};

       for (int i = 0; i < memberNames.length; i++) {
           String profileImage = String.format("/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_%s.png", memberNames[i]);
           Artist artist = Artist.builder()
                   .stageName(memberNames[i])
                   .email("babymonster@gmail.com")
                   .password("BABYMONSTER")
                   .profileImage(profileImage)
                   .group(group)
                   .build();
           artistRepository.save(artist);
       }
   }
   
   @Test
   void insertBLACKPINK() {
      Group group = Group.builder()
            .groupName("BLACKPINK")
            .groupProfileImage("images/BLACKPINK/BLACKPINK_profile/BLACKPINK_group.png")
            .groupLogo("/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_logo.png")
            .build();
      arMapRepository.save(group);
      
      String[] memberNames = {"Jennie", "Jisoo", "Rose", "Lisa"};
      
      for (int i = 0; i < memberNames.length; i++) {
         String profileImage = String.format("/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_%S.png", memberNames[i]);
         Artist artist = Artist.builder()
               .stageName(memberNames[i])
               .email("blackpink@gmail.com")
               .password("BLACKPINK")
               .profileImage(profileImage)
               .group(group)
               .build();
         artistRepository.save(artist);
      }
   }
   
   @Test
   void insert10CM() {
      Group group = Group.builder()
            .groupName("10CM")
            .groupProfileImage("images/10CM/10CM_profile/10CM.png")
            .groupLogo("/images/10CM/10CM_profile/10CM_logo.png")
            .build();
      arMapRepository.save(group);
      
      Artist artist = Artist.builder()
            .stageName("권정열")
            .email("10cm@gmail.com")
            .password("10CM")
            .profileImage("/images/10CM/10CM_profile/10CM.png")
            .group(group)
            .build();
      artistRepository.save(artist);
   }
   /*
   @Test
   public void testAllArtistsAndGroups() {
      ArtistDTO dto = artistController.selectArtist();

      System.out.println("=== 전체 아티스트 리스트 ===");
      dto.getArtistList().forEach(artist -> {
         System.out.println("ID: " + artist.getArtistId() + ", 이름: " + artist.getStageName());
         if (artist.getArtistId() != null) {
            System.out.println("  그룹명: " + artist.getGroup().getGroupName());
         }
      });

      System.out.println("=== 전체 그룹 리스트 ===");
      dto.getGroupList().forEach(group -> {
         System.out.println("그룹ID: " + group.getGroupId() + ", 그룹명: " + group.getGroupName());
      });

   }

   
   @Test
   public void testAllArtistsAndGroups() {
	  ResponseEntity<List<Streaming>> dto = artistController.getStreamingsByGroup(3l);

      System.out.println("=== 전체 아티스트 리스트 ===");
      System.out.println(dto.toString());

   }*/
   
}
