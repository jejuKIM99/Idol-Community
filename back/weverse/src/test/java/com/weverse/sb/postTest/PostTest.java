package com.weverse.sb.postTest;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.ArtistGroupMap;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.controller.PostController;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Post;
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
	void insertNJZPost() {
		
		PostDTO dto = PostDTO.builder()
				.userId(1L)
				.artistId(1L)
				.content("뉴진스 민지 첫번째 게시글 테스트")
				.image("/images/NewJeans_artistpedia_08.png")
				.createdAt(LocalDateTime.now())
				.likeCount(0)
				.build();
	
		
		this.postService.inputPost(dto);
	}
	
	@Test
	public void testSelectPost() {
		PostDTO dto = postController.selectPost();

		System.out.println("=== 전체 게시글 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getId() + ", 작성자: " + post.getArtist().getStageName());
			if (post.getId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ Like : " + post.getLikeCount());
				System.out.println("  ㄴ createAt : " + post.getCreatedAt());
			}
		});

	}
	
//	특정 아티스트 작성 게시글 조회
	@Test
	public void testfilterPost() {
		PostDTO dto = postController.filterPost(1L);

		System.out.println("=== 전체 게시글 리스트 ===");
		dto.getPostList().forEach(post -> {
			System.out.println("ID: " + post.getId() + ", 작성자: " + post.getArtist().getStageName());
			if (post.getId() != null) {
				System.out.println("  ㄴ 내용 : " + post.getContent());
				System.out.println("  ㄴ Like : " + post.getLikeCount());
				System.out.println("  ㄴ createAt : " + post.getCreatedAt());
			}
		});
	}
	
	

}
