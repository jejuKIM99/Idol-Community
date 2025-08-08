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
import com.weverse.sb.user.entity.Favorite;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.FavoriteRepository;
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
	
	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public List<PostDTO> getPostDTOList(Long userId, Long groupId) {
		List<Post> postList = postRepository.findByGroup_GroupIdAndAuthorType(groupId, "artist");
		List<PostDTO> dtoList = new ArrayList<>();

		for (Post post : postList) {
		    int commentCount = commentRepository.countByPostId(post.getId());
		    int likeCount = likeRepository.countByPostId(post.getId());
		    boolean likeByUser = postLikeRepository.existsByUserUserIdAndPostId(userId, post.getId() );
		    boolean followByUser = favoriteRepository.existsByUser_UserIdAndArtist_ArtistId(userId, post.getArtist().getArtistId());
		    
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
		            .likedByUser(likeByUser)
		            .followByUser(followByUser)
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
                .authorType("artist")
                .build();

        postRepository.save(post);
	}

	@Override
	public List<PostDTO> getFilterPostList(Long id) {
		
		List<Post> postList = postRepository.findByArtist_ArtistIdAndAuthorType(id, "artist");
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

	@Override
	@Transactional
	public void insertFavorite(Long artistId, Long userId) {
		
		Artist artist = artistRepository.findById(artistId)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID"));

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

	    if (favoriteRepository.existsByUser_UserIdAndArtist_ArtistId(userId, artistId)) {
	        throw new IllegalStateException("Already followed");
	    }

	    Favorite favorite = Favorite.builder()
	            .artist(artist)
	            .user(user)
	            .createdAt(LocalDateTime.now())
	            .build();

	    favoriteRepository.save(favorite);

		
	}

	@Override
	@Transactional
	public void deleteFavorite(Long artistId, Long userId) {
		
		if (!favoriteRepository.existsByUser_UserIdAndArtist_ArtistId(userId, artistId)) {
            throw new IllegalStateException("follow does not exist");
        }

        favoriteRepository.deleteByUser_UserIdAndArtist_ArtistId(userId, artistId);
		
	}


	@Override
	public List<PostDTO> getFanPostDTOList(Long groupId) {
		
		List<Post> post = postRepository.findByGroup_GroupIdAndAuthorType(groupId, "user");
		List<PostDTO> postList = new ArrayList<>();
		
		
		for (Post fanpost : post) {
			
			int commentCount = commentRepository.countByPostId(fanpost.getId());
			
			PostDTO dto = PostDTO.builder()
					.postId(fanpost.getId())
		            .groupId(groupId)
		            .artistName(fanpost.getArtist().getStageName())
		            .userId(fanpost.getUser().getUserId())
		            .content(fanpost.getContent())
		            .image(fanpost.getImage())
		            .commentCount(commentCount)
		            .createdAt(fanpost.getCreatedAt())
		            .likeCount(fanpost.getLikeCount())
		            .build();
			
			postList.add(dto);
		}
		
		return postList;
		
	}

	@Override
	public void inputFanPost(Long artistID, String content, Long userId) {
		
		Artist artist = artistRepository.findById(artistID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID"));
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
		
        Group group = artist.getGroup();
        
        String groupName = group.getGroupName();
        

		int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
        String formattedNum = String.format("%02d", randomNum);

        String image = "/images/" + groupName + "/" + groupName + "_profile/"  + groupName + "_media_" + formattedNum + ".png";
        
        Post post = Post.builder()
        		.user(user)
                .artist(artist)
                .content(content)
                .image(image)
                .likeCount(0)
                .createdAt(LocalDateTime.now())
                .authorType("user")
                .build();

        postRepository.save(post);
		
	}
	

}
