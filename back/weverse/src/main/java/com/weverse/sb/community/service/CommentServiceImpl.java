package com.weverse.sb.community.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.dto.CommentDTO;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Comment;
import com.weverse.sb.community.entity.Post;
import com.weverse.sb.community.repository.CommentRepository;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ArtistRepository artistRepository;

	@Override
	public List<Comment> getCommentList(Long groupId) {
		return commentRepository.findByGroup_GroupIdAndAuthorType(groupId, "artist");
	}

	@Override
	public void inputComment(Long postId, String content, Long userId, String authorType) {
		
		Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        if ("artist".equals(authorType)) {
        	
        	Artist artist = artistRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID"));
        	
        	
        	Comment comment = Comment.builder()
            		.artist(artist)
            		.group(artist.getGroup())
            		.createdAt(LocalDateTime.now())
                    .post(post)
                    .content(content)
                    .authorType(authorType)
                    .build();
        	
        	commentRepository.save(comment);
        	
		}else {
			
			User user = userRepository.findById(userId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
			
			Comment comment = Comment.builder()
	        		.createdAt(LocalDateTime.now())
	                .post(post)
	                .user(user)
	                .content(content)
	                .authorType(authorType)
	                .build();
			
			commentRepository.save(comment);

		}
		
	}

	@Override
	public int getCommentCountByPostId(Long postId) {
		return commentRepository.countByPostId(postId);
	}


	

}
