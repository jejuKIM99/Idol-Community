package com.weverse.sb.postTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.controller.FanPostController;
import com.weverse.sb.community.controller.PostController;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.community.service.PostService;

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

	@Test
	void insertFanPost() {

		Long artistID = 5L;
		Long userId = 1L;
		String content = "혜인";

		this.postService.inputFanPost(artistID, content, userId);
	}
//	
//	@Test
//	public void testSelectArtist() {
//		Long artistId = 1L;
//		ArtistDTO dto = postController.selectArtist(artistId);
//
//		System.out.println("=== 아티스트 정보 ===");
//	    System.out.println("ID: " + dto.getId() + ", 이름: " + dto.getStageName());
//	    System.out.println("  ㄴ 프로필사진 : " + dto.getProfileImage());
//	    System.out.println("  ㄴ 이메일 : " + dto.getEmail());
//	    System.out.println("  ㄴ 그룹명 : " + dto.getGroupName());
//	}

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

//	//	특정 아티스트 작성 게시글 조회
//	@Test
//	public void testfilterPost() {
//		PostDTO dto = postController.filterPost(6L);
//
//		System.out.println("=== 전체 게시글 리스트 ===");
//		dto.getPostList().forEach(post -> {
//			System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getArtistName());
//			if (post.getPostId() != null) {
//				System.out.println("  ㄴ 내용 : " + post.getContent());
//				System.out.println("  ㄴ createAt : " + post.getCreatedAt());
//				System.out.println("  ㄴ Like : " + post.getLikeCount());
//				System.out.println("  ㄴ commentCount : " + post.getCommentCount());
//			}
//		});
//	}
//
	//	특정 아티스트 작성 게시글 좋아요
//	@Test
//	public void testLikePost() {
//		Long postId = 3L;
//		Long userId = 1L;
//
//		postService.inputPostLike(postId, userId);
//	}
//
//	//	특정 아티스트 작성 게시글 좋아요 취소
//	@Test
//	public void testUnlikePost() {
//		Long postId = 3L;
//		Long userId = 1L;
//
//		postService.deletePostLike(postId, userId);
//	}
//	
	//	특정 아티스트 팔로우
//	@Test
//	public void testFollowed() {
//		Long artistId = 2L;
//		Long userId = 1L;
//		
//		postService.insertFavorite(artistId, userId);
//	}
//	
//	//	특정 아티스트 팔로우 취소
//	@Test
//	public void testUnFollowed() {
//		Long artistId = 3L;
//		Long userId = 1L;
//		
//		postService.deleteFavorite(artistId, userId);
//	}



}
