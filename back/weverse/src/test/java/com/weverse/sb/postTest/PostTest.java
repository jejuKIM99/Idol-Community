package com.weverse.sb.postTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;
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

	@Test
	void insertPost() {

		Long artistID = 6L;
		String content = "르세라핌 사쿠라 첫번째 게시글 테스트";

		this.postService.inputPost(artistID, content);
	}

	@Test
	public void testSelectPost() {
		PostDTO dto = postController.selectPost();

		System.out.println("=== 전체 게시글 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getArtistName());
			if (post.getPostId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ createAt : " + post.getCreatedAt());
				System.out.println("  ㄴ Like : " + post.getLikeCount());
				System.out.println("  ㄴ commentCount : " + post.getCommentCount());
			}
		});
	}

	//	특정 아티스트 작성 게시글 조회
	@Test
	public void testfilterPost() {
		PostDTO dto = postController.filterPost(6L);

		System.out.println("=== 전체 게시글 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getPostId() + ", 작성자: " + post.getArtistName());
			if (post.getPostId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ createAt : " + post.getCreatedAt());
				System.out.println("  ㄴ Like : " + post.getLikeCount());
				System.out.println("  ㄴ commentCount : " + post.getCommentCount());
			}
		});
	}

	//	특정 아티스트 작성 게시글 좋아요
	@Test
	public void testLikePost() {
		Long postId = 3L;
		Long userId = 1L;

		postService.inputPostLike(postId, userId);
	}

	//	특정 아티스트 작성 게시글 좋아요 취소
	@Test
	public void testUnlikePost() {
		Long postId = 3L;
		Long userId = 1L;

		postService.deletePostLike(postId, userId);
	}



}
