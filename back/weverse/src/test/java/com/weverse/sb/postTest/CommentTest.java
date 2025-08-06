package com.weverse.sb.postTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.controller.CommentController;
import com.weverse.sb.community.controller.PostController;
import com.weverse.sb.community.dto.CommentDTO;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.community.service.CommentService;
import com.weverse.sb.community.service.PostService;

@SpringBootTest
public class CommentTest {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentController commentController;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Test
	void insertNJZPostComment() {
		
		CommentDTO dto = CommentDTO.builder()
				.artistId(1L)
				.postId(1L)
				.userId(1L)
				.content("뉴진스 민지 첫번째 댓글 테스트")
				.build();
	
		
		this.commentService.inputComment(dto);
	}
	
	@Test
	public void testfilterComment() {
		CommentDTO dto = commentController.selectPostCommend(1L);

		System.out.println("=== 전체 게시글 리스트 ===");
		dto.getCommentList().forEach(comment -> {
			System.out.println("ID: " + comment.getId() + ", 작성자: " + comment.getUser().getName());
			if (comment.getId() != null) {
				System.out.println("  ㄴ 내용 : " + comment.getContent());
				System.out.println("  ㄴ 원글 : " + comment.getPost().getContent());
				System.out.println("  ㄴ createAt : " + comment.getCreatedAt());
			}
		});
	}
	
	

}
