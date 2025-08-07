package com.weverse.sb.postTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.community.controller.CommentController;
import com.weverse.sb.community.dto.CommentDTO;
import com.weverse.sb.community.service.CommentService;

@SpringBootTest
public class CommentTest {
	

	@Autowired
	private CommentController commentController;
	
	@Autowired
	private CommentService commentService;
	
	@Test
	void insertPostComment() {
		
		Long postId = 3L;
		String content = "르세라핌 댓글 테스트";
		Long userId = 2L;
		
		this.commentService.inputComment(postId, content, userId);
	}
	
	@Test
	public void testfilterComment() {
		CommentDTO dto = commentController.selectPostCommend(3L);

		System.out.println("=== 게시글 별 댓글 리스트 ===");
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
