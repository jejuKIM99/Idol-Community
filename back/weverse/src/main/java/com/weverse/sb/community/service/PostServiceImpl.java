package com.weverse.sb.community.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Post;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private ArtistRepository artistRepository;

	@Override
	public List<Post> getList() {
		return postRepository.findAll();
	}

	@Override
	public void inputPost(PostDTO dto) {
		User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID"));
        
        Post post = Post.builder()
                .user(user)
                .artist(artist)
                .content(dto.getContent())
                .image(dto.getImage())
                .likeCount(0)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
	}

	@Override
	public List<Post> getFilterPostList(Long id) {
		// TODO Auto-generated method stub
		return postRepository.findByArtistId(id);
	}

}
