package com.weverse.sb.userTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.controller.ArtistController;
import com.weverse.sb.artist.controller.BoardController;
import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.dto.BoardDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.artist.service.BoardService;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;

@SpringBootTest
class BoardTest {

	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardController boardController;


	@Test
	void testinsertBoard() {
		
		/*
		 * 	카테고리 테이블은 INSERT 테스트 코드를 만드는게 더 번거로워서 아래 INSERT 문 그냥 SQL 로 실행시키고
		 * 	테스트 코드 실행 부탁드립니다
		 * 	insert into board_category (board_category_id, title) value('1', "공지사항");
			insert into board_category (board_category_id, title) value('2', "안내");
		 * 
		 */

		Long catagoryId = 1L;
		Long groupId = 2L;
		String title = "2025 NewJeans CONCERT [Milky Way] 공식 MD 온라인 판매 안내";
		String content = "2025 NewJeans CONCERT [Milky Way] 공식 MD 온라인 판매 안내입니다.\n"
				+ "2025 NewJeans CONCERT [Milky Way] 공식 MD는 사운드웨이브 온라인몰에서 판매됩니다.\n"
				+ "자세한 사항은 아래 내용을 참고해 주시기 바랍니다.";

		this.boardService.inputNotice(catagoryId, groupId, title, content);
	}
	
	@Test
	public void testSelectBoard() {
		
		Long groupId = 1L;
		
		BoardDTO dto = boardController.selectNotice(groupId);

		System.out.println("=== 전체 공지사항 리스트 ===");
		dto.getBoardList().forEach(notice -> {
			System.out.println("ID: " + notice.getId() + ", 그룹: " + notice.getGroupName());
			if (notice.getId() != null) {
				System.out.println("  ㄴ 카테고리 : " + notice.getCatagoryName());
				System.out.println("  ㄴ 제목 : " + notice.getTitle());
				System.out.println("  ㄴ 내용 : " + notice.getContent());
				System.out.println("  ㄴ 작성일 : " + notice.getCreatedAt());
			}
		});
	}
	
	

}
