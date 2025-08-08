package com.weverse.sb.userTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.controller.ArtistController;
import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;

@SpringBootTest
class ArtistTest {

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	ArtistController artistController;

	
//	@Test
//	void insertNJZ() {
//		Group group = Group.builder()
//				.groupName("NewJeans")
//				.groupProfileImage("/images/NewJeans_profile/NewJeans_group.png")
//				.groupLogo("/images/NewJeans_profile/NewJeans_logo.png")
//				.build();
//		groupRepository.save(group);
//		
//		String[] memberNames = {"민지", "하니", "다니엘", "해린", "혜인"};
//		String[] memberEngNames = {"Minji", "Hanny", "Daniel", "Haerin", "Hyein"};
//		
//		for (int i = 0; i < memberNames.length; i++) {
//			String profileImage = String.format("/images/NewJeans_profile/NewJeans_" + memberEngNames[i] + ".png");
//			Artist artist = Artist.builder()
//					.group(group)
//					.stageName(memberNames[i])
//					.email("njz_" + memberEngNames[i] + "@gmail.com")
//					.password("NewJeans")
//					.profileImage(profileImage)
//					.build();
//			artistRepository.save(artist);
//		}
//	}
//	
//	@Test
//	void insertLESSERAFIM() {
//	    Group group = Group.builder()
//	            .groupName("LESSERAFIM")
//	            .groupProfileImage("/images/LESSERAFIM_profile/LESSERAFIM_group.png")
//	            .groupLogo("/images/LESSERAFIM_profile/LESSERAFIM_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"사쿠라", "김채원", "허윤진", "카즈하", "홍은채"};
//	    String[] memberEngNames = {"Sakura", "KimChaewon", "HuhYunjin", "Kazuha", "HongEunchae"};
//
//	    for (int i = 0; i < memberNames.length; i++) {
//	        String profileImage = String.format("/images/LESSERAFIM_profile/" + memberEngNames[i] + ".png");
//	        Artist artist = Artist.builder()
//	        		.group(group)
//	                .stageName(memberNames[i])
//	                .email("lesserafim_" + memberEngNames[i] + "@gmail.com")
//	                .password("LESSERAFIM")
//	                .profileImage(profileImage)
//	                .group(group)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insertNCTWISH() {
//	    Group group = Group.builder()
//	            .groupName("NCT_WISH")
//	            .groupProfileImage("/images/NCT_WISH_profile/NCT_WISH_group.png")
//	            .groupLogo("/images/NCT_WISH_profile/NCT_WISH_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"시온", "리쿠", "유우시", "재희", "료", "사쿠야"};
//	    String[] memberEngNames = {"Sion", "Riku", "Yushi", "Jaehee", "Ryo", "Sakuya"};
//
//	    for (int i = 0; i < memberNames.length; i++) {
//	        String profileImage = String.format("/images/NCT_WISH_profile/NCT_WISH_" + memberEngNames[i] + ".png");
//	        Artist artist = Artist.builder()
//	                .group(group)
//	                .stageName(memberNames[i])
//	                .email("nctwish_" + memberEngNames[i] + "@gmail.com")
//	                .password("NCT_WISH")
//	                .profileImage(profileImage)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insertRIIZE() {
//	    Group group = Group.builder()
//	            .groupName("RIIZE")
//	            .groupProfileImage("/images/RIIZE_profile/RIIZE_group.png")
//	            .groupLogo("/images/RIIZE_profile/RIIZE_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"쇼타로", "은석", "성찬", "원빈", "소희", "앤톤"};
//	    String[] memberEngNames = {"Shotaro", "Eunseok", "Sungchan", "Wonbin", "Sohee", "Anton"};
//
//	    for (int i = 0; i < memberNames.length; i++) {
//	        String profileImage = String.format("/images/RIIZE_profile/RIIZE_" + memberEngNames[i] + ".png");
//	        Artist artist = Artist.builder()
//	                .group(group)
//	                .stageName(memberNames[i])
//	                .email("riize_" + memberEngNames[i] + "@gmail.com")
//	                .password("RIIZE")
//	                .profileImage(profileImage)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insertSTAYC() {
//	    Group group = Group.builder()
//	            .groupName("STAYC")
//	            .groupProfileImage("/images/STAYC_profile/STAYC_group.png")
//	            .groupLogo("/images/STAYC_profile/STAYC_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"수민", "시은", "아이사", "세은", "윤", "재이"};
//	    String[] memberEngNames = {"Sumin", "Sieun", "Isa", "Seeun", "Yoon", "Jaei"};
//
//	    for (int i = 0; i < memberNames.length; i++) {
//	        String profileImage = String.format("/images/STAYC_profile/STAYC_" + memberEngNames[i] + ".png");
//	        Artist artist = Artist.builder()
//	                .group(group)
//	                .stageName(memberNames[i])
//	                .email("stayc_" + memberEngNames[i] + "@gmail.com")
//	                .password("STAYC")
//	                .profileImage(profileImage)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insertTWS() {
//	    Group group = Group.builder()
//	            .groupName("TWS")
//	            .groupProfileImage("/images/TWS_profile/TWS_group.png")
//	            .groupLogo("/images/TWS_profile/TWS_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"신유", "도훈", "영재", "한진", "지훈", "경민"};
//	    String[] memberEngNames = {"Shinyu", "Dohoon", "Youngjae", "Hanjin", "Jihoon", "Gyeongmin"};
//
//	    for (int i = 0; i < memberNames.length; i++) {
//	        String profileImage = String.format("/images/TWS_profile/TWS_" + memberEngNames[i] + ".png");
//	        Artist artist = Artist.builder()
//	                .group(group)
//	                .stageName(memberNames[i])
//	                .email("tws_" + memberEngNames[i] + "@gmail.com")
//	                .password("TWS")
//	                .profileImage(profileImage)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insertBABYMONSTER() {
//	    Group group = Group.builder()
//	            .groupName("BABYMONSTER")
//	            .groupProfileImage("/images/BABYMONSTER_profile/BABYMONSTER_group.png")
//	            .groupLogo("/images/BABYMONSTER_profile/BABYMONSTER_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"ruka", "pharita", "asa", "ahyeon", "rami", "rora", "chiquita"};
//
//	    for (String memberName : memberNames) {
//	        String profileImage = String.format("/images/BABYMONSTER_profile/BABYMONSTER_" + memberName + ".png");
//	        Artist artist = Artist.builder()
//	                .group(group)
//	                .stageName(memberName)
//	                .email("babymonster_" + memberName.toLowerCase() + "@gmail.com")
//	                .password("BABYMONSTER")
//	                .profileImage(profileImage)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insertBLACKPINK() {
//	    Group group = Group.builder()
//	            .groupName("BLACKPINK")
//	            .groupProfileImage("/images/BLACKPINK_profile/BLACKPINK_group.png")
//	            .groupLogo("/images/BLACKPINK_profile/BLACKPINK_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String[] memberNames = {"Junnie", "Jisoo", "Rose", "Lisa"};
//
//	    for (String memberName : memberNames) {
//	        String profileImage = String.format("/images/BLACKPINK_profile/BLACKPINK_" + memberName + ".png");
//	        Artist artist = Artist.builder()
//	                .group(group)
//	                .stageName(memberName)
//	                .email("blackpink_" + memberName.toLowerCase() + "@gmail.com")
//	                .password("BLACKPINK")
//	                .profileImage(profileImage)
//	                .build();
//	        artistRepository.save(artist);
//	    }
//	}
//
//	@Test
//	void insert10CM() {
//	    Group group = Group.builder()
//	            .groupName("10CM")
//	            .groupProfileImage("/images/10CM_profile/10CM.png")
//	            .groupLogo("/images/10CM_profile/10CM_logo.png")
//	            .build();
//	    groupRepository.save(group);
//
//	    String profileImage = "/images/10CM_profile/10CM.png";
//
//	    Artist artist = Artist.builder()
//	            .group(group)
//	            .stageName("권정열")
//	            .email("10cm@gmail.com")
//	            .password("10CM")
//	            .profileImage(profileImage)
//	            .build();
//	    artistRepository.save(artist);
//	}
//
//	
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

}
