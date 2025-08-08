package com.weverse.sb.postTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.BoardDTO;
import com.weverse.sb.artist.dto.GroupDTO;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.controller.FanPostController;
import com.weverse.sb.community.controller.PostController;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.community.service.PostService;
import com.weverse.sb.user.dto.UserDTO;

@SpringBootTest
public class FanPostTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostController postController;
	
	@Autowired
	FanPostController fanPostController;

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	private PostService postService;
/*
	@Test
	void insertFanPost() {

		Long groupId = 1L;
		Long userId = 1L;
		String content = "혜인";

		this.postService.inputFanPost(groupId, content, userId);
	}
	
	@Test
	public void testGroupInfo() {
		Long groupId = 1L;
		GroupDTO dto = fanPostController.groupInfo(groupId);

		System.out.println("=== 그룹 정보 ===");
		System.out.println("  ㄴ 그룹명 : " + dto.getGroupName());
		System.out.println("  ㄴ 그룹ID : " + dto.getGroupId());
	    System.out.println("  ㄴ 그룹사진 : " + dto.getGroupProfileImage());
	    System.out.println("  ㄴ 그룹로고 : " + dto.getGroupLogo());
	}*/

	@Test
	public void testSelectFanPost() {
		Long groupId = 1L;
		
		PostDTO dto = fanPostController.selectFanPost(groupId);

		System.out.println("=== 전체 팬게시판 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getUserId());
			if (post.getPostId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ 작성일 : " + post.getCreatedAt());
				System.out.println("  ㄴ 좋아요 개수 : " + post.getLikeCount());
				System.out.println("  ㄴ 댓글수 : " + post.getCommentCount());
				System.out.println("  ㄴ 좋아요한 게시글 여부 (사용자) : " + post.isLikedByUser());
				System.out.println("  ㄴ 아티스트 : " + post.getArtistName());
			}
		});
	}

	// 아티스트 최근 게시글 조회
	@Test
	public void testlatestPost() {
		Long groupId = 1L;
		Long userId =1L;
		PostDTO dto = fanPostController.latestPost(userId, groupId);

		System.out.println("=== 최근 게시글 리스트 ===");
		
		List<PostDTO> postList = dto.getPostList();

		for (int i = 0; i < postList.size() && i < 9; i++) {
		    PostDTO post = postList.get(i);
		    System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getArtistName());
		    if (post.getPostId() != null) {
		        System.out.println("  ㄴ 내용 : " + post.getContent());
		        System.out.println("  ㄴ createAt : " + post.getCreatedAt());
		        System.out.println("  ㄴ Like : " + post.getLikeCount());
		        System.out.println("  ㄴ commentCount : " + post.getCommentCount());
		    }
		}
	}
	
	// 사용자 정보 조회
	@Test
	public void testUserInfo() {
		Long userId = 1L;
		UserDTO dto = fanPostController.userInfo(userId);

		System.out.println("=== 내 정보 ===");
		System.out.println("  ㄴ 아이디 : " + dto.getUserId());
		System.out.println("  ㄴ 이름 : " + dto.getName());
	    System.out.println("  ㄴ 이메일 : " + dto.getEmail());
	    System.out.println("  ㄴ 닉네임 : " + dto.getNickname());
	}
	
	// 공지사항 조회
	@Test
	public void testNoticeInfo() {
		Long groupId = 1L;
		BoardDTO dto = fanPostController.noticeInfo(groupId);
		
		List<BoardDTO> boardList = dto.getBoardList();
		
		for (int i = 0; i < boardList.size() && i < 4; i++) {
			BoardDTO board = boardList.get(i);
			
			System.out.println("=== 공지사항 ===");
			System.out.println("  ㄴ 그룹명 : " + board.getGroupName());
			System.out.println("  ㄴ 제목 : " + board.getTitle());
		    System.out.println("  ㄴ 내용 : " + board.getContent());
		    System.out.println("  ㄴ 작성일 : " + board.getCreatedAt());
			
		}

	}

}
