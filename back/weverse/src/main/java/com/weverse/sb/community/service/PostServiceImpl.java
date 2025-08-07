package com.weverse.sb.community.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Post;
import com.weverse.sb.community.entity.PostLike;
import com.weverse.sb.community.repository.CommentRepository;
import com.weverse.sb.community.repository.PostLikeRepository;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
    private ArtistRepository artistRepository;
	
	@Autowired
    private CommentRepository commentRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostLikeRepository likeRepository;

	@Override
	public List<PostDTO> getPostDTOList() {
		List<Post> postList = postRepository.findAll();
		List<PostDTO> dtoList = new ArrayList<>();

		for (Post post : postList) {
		    int commentCount = commentRepository.countByPostId(post.getId());
		    int likeCount = likeRepository.countByPostId(post.getId());
		    Long artistId = (post.getArtist() != null) ? post.getArtist().getArtistId() : null;

		    PostDTO dto = PostDTO.builder()
		            .postId(post.getId())
		            .content(post.getContent())
		            .image(post.getImage())
		            .likeCount(likeCount)
		            .createdAt(post.getCreatedAt())
		            .artistId(artistId)
		            .artistName(post.getArtist().getStageName())
		            .commentCount(commentCount)
		            .build();

		    dtoList.add(dto);
		}

		return dtoList;
    }

	@Override
	public void inputPost(Long artistID, String content) {
		
		Artist artist = artistRepository.findById(artistID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID"));
        
        Group group = artist.getGroup();
        
        String groupName = group.getGroupName();

		int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
        String formattedNum = String.format("%02d", randomNum);

        String image = "/images/" + groupName + "/" + groupName + "_profile/"  + groupName + "_media_" + formattedNum + ".png";
        
        Post post = Post.builder()
                .artist(artist)
                .content(content)
                .image(image)
                .likeCount(0)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
	}

	@Override
	public List<PostDTO> getFilterPostList(Long id) {
		
		List<Post> postList = postRepository.findByArtistArtistId(id);
		List<PostDTO> dtoList = new ArrayList<>();

		for (Post post : postList) {
		    int commentCount = commentRepository.countByPostId(post.getId());
		    Long artistId = post.getArtist().getArtistId();

		    PostDTO dto = PostDTO.builder()
		            .postId(post.getId())
		            .content(post.getContent())
		            .image(post.getImage())
		            .likeCount(post.getLikeCount())
		            .createdAt(post.getCreatedAt())
		            .artistId(artistId)
		            .commentCount(commentCount)
		            .artistName(post.getArtist().getStageName())
		            .build();

		    dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
    @Transactional
    public void inputPostLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // 좋아요 중복 방지 체크
        if (postLikeRepository.existsByUserUserIdAndPostId(userId, postId)) {
            throw new IllegalStateException("Already liked");
        }

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(user)
                .build();

        postLikeRepository.save(postLike);

        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePostLike(Long postId, Long userId) {

        if (!postLikeRepository.existsByUserUserIdAndPostId(userId, postId)) {
            throw new IllegalStateException("Like does not exist");
        }

        postLikeRepository.deleteByUserUserIdAndPostId(userId, postId);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        post.setLikeCount(post.getLikeCount() - 1);
        postRepository.save(post);
    }
	

}
