package com.weverse.sb.userTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.controller.ArtistController;
import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.artist.repository.ArtistRepository;

@SpringBootTest
class ArtistTest {

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	ArtistController artistController;

	
	@Test
	void insertNJZ() {
		Group group = Group.builder()
				.groupName("NewJeans")
				.groupProfileImage("/images/NewJeans_artistpedia_11.png")
				.groupLogo("/images/NewJeans_artistpedia_01.png")
				.build();
		groupRepository.save(group);
		
		String[] memberNames = {"민지", "하니", "다니엘", "해린", "혜인"};
		
		for (int i = 0; i < memberNames.length; i++) {
			String profileImage = String.format("/images/NewJeans_artistpedia_%02d.png", i + 2);
			Artist artist = Artist.builder()
					.group(group)
					.stageName(memberNames[i])
					.email("njz@gmail.com")
					.password("NewJeans")
					.profileImage(profileImage)
					.build();
			artistRepository.save(artist);
		}
	}
	
	@Test
	void insertLESSERAFIM() {
	    Group group = Group.builder()
	            .groupName("LESSERAFIM")
	            .groupProfileImage("/images/LESSERAFIM_artistpedia_06.png")
	            .groupLogo("/images/LESSERAFIM_artistpedia_07.png")
	            .build();
	    groupRepository.save(group);

	    String[] memberNames = {"사쿠라", "김채원", "허윤진", "카즈하", "홍은채"};

	    for (int i = 0; i < memberNames.length; i++) {
	        String profileImage = String.format("/images/LESSERAFIM_artistpedia_%02d.png", i + 1);
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
	            .groupProfileImage("/images/NCT_WISH_artistpedia_01.png")
	            .groupLogo("/images/NCT_WISH_artistpedia_08.png")
	            .build();
	    groupRepository.save(group);

	    String[] memberNames = {"시온", "리쿠", "유우시", "재희", "료", "사쿠야"};

	    for (int i = 0; i < memberNames.length; i++) {
	        String profileImage = String.format("/images/NCT_WISH_artistpedia%02d.png", i + 2);
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
	            .groupProfileImage("/images/RIIZE_artistpedia02.png")
	            .groupLogo("/images/RIIZE_artistpedia01.png")
	            .build();
	    groupRepository.save(group);

	    String[] memberNames = {"쇼타로", "은석", "성찬", "원빈", "소희", "앤톤"};

	    for (int i = 0; i < memberNames.length; i++) {
	        String profileImage = String.format("/images/RIIZE_artistpedia%02d.png", i + 3);
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
	            .groupProfileImage("/images/STAYC_artistpedia_07.png")
	            .groupLogo("/images/STAYC_artistpedia_08.png")
	            .build();
	    groupRepository.save(group);

	    String[] memberNames = {"수민", "시은", "아이사", "세은", "윤", "재이"};

	    for (int i = 0; i < memberNames.length; i++) {
	        String profileImage = String.format("/images/STAYC_artistpedia_%02d.png", i + 1);
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
	            .groupProfileImage("/images/TWS_artistpedia_02.png")
	            .groupLogo("/images/TWS_artistpedia_01.png")
	            .build();
	    groupRepository.save(group);

	    String[] memberNames = {"신유", "도훈", "영재", "한진", "지훈", "경민"};

	    for (int i = 0; i < memberNames.length; i++) {
	        String profileImage = String.format("/images/TWS_artistpedia%02d.png", i + 3);
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
	            .groupProfileImage("/images/BABYMONSTER_artistpedia_02.png")
	            .groupLogo("/images/BABYMONSTER_artistpedia_01.png")
	            .build();
	    groupRepository.save(group);

	    String[] memberNames = {"RUKA", "PHARITA", "ASA", "AHYEON", "RAMI", "RORA", "CHIQUITA"};

	    for (int i = 0; i < memberNames.length; i++) {
	        String profileImage = String.format("/images/BABYMONSTER_artistpedia%02d.png", i + 3);
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
				.groupProfileImage("/images/BLACKPINK_artistpedia_02.png")
				.groupLogo("/images/BLACKPINK_artistpedia_01.png")
				.build();
		groupRepository.save(group);
		
		String[] memberNames = {"JUNNIE", "JISOO", "ROSÉ", "LISA"};
		
		for (int i = 0; i < memberNames.length; i++) {
			String profileImage = String.format("/images/BLACKPINK_artistpedia%02d.png", i + 3);
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
				.groupProfileImage("/images/10CM_artistpedia_02.png")
				.groupLogo("/images/10CM_artistpedia01.png")
				.build();
		groupRepository.save(group);
		
		String profileImage = String.format("/images/10CM_artistpedia03.png");
		Artist artist = Artist.builder()
				.stageName("권정열")
				.email("10cm@gmail.com")
				.password("10CM")
				.profileImage(profileImage)
				.group(group)
				.build();
		artistRepository.save(artist);
	}
	
	@Test
	public void testAllArtistsAndGroups() {
		ArtistDTO dto = artistController.selectArtist();

		System.out.println("=== 전체 아티스트 리스트 ===");
		dto.getArtistList().forEach(artist -> {
			System.out.println("ID: " + artist.getId() + ", 이름: " + artist.getStageName());
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
