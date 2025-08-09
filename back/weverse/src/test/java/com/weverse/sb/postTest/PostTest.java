package com.weverse.sb.postTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.ArtistInfoResponseDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.controller.PostController;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.community.service.PostService;

@SpringBootTest
public class PostTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostController postController;

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	private PostService postService;

	/*@Test
	void insertPost() {

		Long artistID = 9L;
		String content = "카즈하 첫번째 게시글";

		this.postService.inputPost(artistID, content);
	}
	
	@Test
	public void testSelectArtist() {
		Long artistId = 1L;
		Artist artist = postController.selectArtist(artistId);

		System.out.println("=== 아티스트 정보 ===");
	    System.out.println("ID: " + artist.getArtistId() + ", 이름: " + artist.getStageName());
	    System.out.println("  ㄴ 프로필사진 : " + artist.getProfileImage());
	    System.out.println("  ㄴ 이메일 : " + artist.getEmail());
	}
	
	@Test
	public void testSelectGroupArtist() {
		Long groupId = 1L;
		ArtistInfoResponseDTO dto = postController.selectGroupArtist(groupId);
		
		System.out.println("=== 그룹 멤버 정보 ===");
		
		dto.getArtists().forEach(artist -> {
			if (artist.getArtistId() != null) {
				System.out.println("ID: " + artist.getArtistId() + ", 이름: " + artist.getStageName());
				System.out.println("  ㄴ 프로필사진 : " + artist.getProfileImage());
				System.out.println("  ㄴ 이메일 : " + artist.getEmail());
			}
		});
		
		
	}

	@Test
	public void testSelectPost() {
		Long userId = 1L;
		Long groupId = 1L;
		PostDTO dto = postController.selectPost(userId, groupId);

		System.out.println("=== 전체 게시글 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getArtistName());
			if (post.getPostId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ 작성일 : " + post.getCreatedAt());
				System.out.println("  ㄴ 좋아요 개수 : " + post.getLikeCount());
				System.out.println("  ㄴ 댓글수 : " + post.getCommentCount());
				System.out.println("  ㄴ 좋아요한 게시글 여부 (사용자) : " + post.isLikedByUser());
				System.out.println("  ㄴ 팔로우 여부 (사용자) : " + post.isFollowByUser());
			}
		});
	}

	//	특정 아티스트 작성 게시글 조회
	@Test
	public void testfilterPost() {
		PostDTO dto = postController.filterPost(3L);

		System.out.println("=== 특정 아티스트 게시글 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getArtistName());
			if (post.getPostId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ createAt : " + post.getCreatedAt());
				System.out.println("  ㄴ Like : " + post.getLikeCount());
				System.out.println("  ㄴ commentCount : " + post.getCommentCount());
			}
		});
	}*/
//
	//	특정 아티스트 작성 게시글 좋아요
	@Test
	public void testLikePost() {
		Long postId = 9L;
		Long userId = 3L;

		postService.inputPostLike(postId, userId);
	}
//
	//	특정 아티스트 작성 게시글 좋아요 취소
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
